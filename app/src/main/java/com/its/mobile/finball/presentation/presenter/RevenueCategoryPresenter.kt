package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.RevenueCategoryInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.view.RevenueCategoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RevenueCategoryPresenter(private val revenueCategoryInteract: RevenueCategoryInteract) :
    BaseMvpPresenter<RevenueCategoryView>(), CategoryAdapterContract.AdapterPresenter,
    CategoryAdapterContract.CategoryItemPresenter {

    private val categoryList: ArrayList<CategoryEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        categoryList.addAll(revenueCategoryInteract.getCategoryList())
        loadRevenueList()
    }

    override fun getItemsCount(): Int = categoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = categoryList[position]
        view.setIcon(item.iconId)
        view.setText(item.titleId)
    }

    override fun onCategoryItemClicked(position: Int) {
        val category = categoryList[position]
        if (category.isDynamic)
            viewState.navigateToSubCategory(category.id)
        else
            viewState.navigateToInputRevenueAmount(category.id)
    }

    override fun onCategoryItemLongClick(position: Int): Boolean = false

    private fun loadRevenueList() {
        revenueCategoryInteract.loadRevenueList()
            .subscribeOn(Schedulers.io())
            .map { list ->
                mapRevenueListToCategoryList(list)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                },
                onError = {}
            )
            .let { disposables.add(it) }
    }

    private fun mapRevenueListToCategoryList(revenueList: List<RevenueEntity>) {
        categoryList.forEach { category ->
            category.amount = 0f
            revenueList
                .filter { revenueEntity -> revenueEntity.categoryId == category.id }
                .forEach {
                    if (category.id == it.categoryId) {
                        category.rating++
                    }
                }
            if (category.isDynamic) {
                getTotalRatingOnSubCategory(category, revenueList)
            }
        }
    }

    private fun getTotalRatingOnSubCategory(parentCategory: CategoryEntity, revenueList: List<RevenueEntity>) {
        revenueCategoryInteract.loadRevenueSubCategory(parentCategory.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { subCategoryList ->
                    subCategoryList.forEach { subCategory ->
                        revenueList
                            .sortedBy { revenueEntity -> revenueEntity.categoryId == subCategory.id }
                            .forEach {
                                if (subCategory.id == it.categoryId) {
                                    parentCategory.rating++
                                }
                            }
                    }
                    categoryList.sortByDescending { it.rating }
                    viewState.notifyDataSetChanged()
                },
                {}
            )
            .let { disposables.add(it) }
    }
}