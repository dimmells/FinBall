package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.RevenueRatingInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.view.RevenueRatingView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RevenueRatingPresenter(private val revenueRatingInteract: RevenueRatingInteract): BaseMvpPresenter<RevenueRatingView>(), CategoryAdapterContract.AdapterPresenter, CategoryAdapterContract.CategoryItemPresenter {

    private val revenueCategoryList: ArrayList<CategoryEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            insert(RevenueEntity(java.util.Date(969656400000), 1, 250.90f))
            insert(RevenueEntity(java.util.Date(969743800000), 4, 500f))
            insert(RevenueEntity(java.util.Date(969742800000), 2, 0.90f))
            insert(RevenueEntity(java.util.Date(969915600000), 2, 0f))
            insert(RevenueEntity(java.util.Date(970002000000), 1, 1250.40f))
            insert(RevenueEntity(java.util.Date(970088400000), 1, 25f))
            insert(RevenueEntity(java.util.Date(970174800000), 1, 50.45f))
        //DELETE!!!!!!!!!!!!!!!!!!!!!
        revenueCategoryList.addAll(revenueRatingInteract.getRevenueCategories())
        loadRevenueList()
    }

    override fun getItemsCount(): Int = revenueCategoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = revenueCategoryList[position]
        view.setIcon(item.iconId)
        view.setText(item.titleId)
        view.setAmount(item.amount)
    }

    override fun onCategoryItemClicked(position: Int) {}

    override fun onCategoryItemLongClick(position: Int): Boolean = false

    private fun loadRevenueList() {
        revenueRatingInteract.loadRevenueList()
            .subscribeOn(Schedulers.io())
            .map { list ->
                mapRevenueListToCategoryList(list)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = { viewState.notifyDataSetChanged() },
                onError = { viewState.showToast(it.localizedMessage) } )
            .let { disposables.add(it) }
    }

    private fun mapRevenueListToCategoryList(revenueList: List<RevenueEntity>) {
        revenueCategoryList.forEach { category ->
            category.amount = 0f
            revenueList
                .sortedBy { revenueEntity -> revenueEntity.categoryId == category.id }
                .forEach {
                    if (category.id == it.categoryId) { category.amount += it.amount }
                }
            if (category.isDynamic) { getTotalRevenueOnSubCategory(category, revenueList) }
        }
    }

    private fun getTotalRevenueOnSubCategory(parentCategory: CategoryEntity, revenueList: List<RevenueEntity>) {
        revenueRatingInteract.loadRevenueSubCategory(parentCategory.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { subCategoryList ->
                    subCategoryList.forEach { subCategory ->
                        revenueList
                            .sortedBy { revenueEntity -> revenueEntity.categoryId == subCategory.id }
                            .forEach {
                                if (subCategory.id == it.categoryId) { parentCategory.amount += it.amount }
                            }
                    }
                    sortList()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun sortList() {
        val sortedList = revenueCategoryList
            .sortedByDescending { list -> list.amount }
        revenueCategoryList.clear()
        revenueCategoryList.addAll(sortedList)
    }

    private fun insert(revenueEntity: RevenueEntity) {
        revenueRatingInteract.insertTest(revenueEntity)
        .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                viewState.showToast("Error: ${throwable.localizedMessage}")
            }
            .doOnSuccess {
            }
            .subscribe()
            .let { disposables.add(it) }
    }
}