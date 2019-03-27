package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.CostsCategoryInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.view.CostsCategoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class CostsCategoryPresenter(private val costsCategoryInteract: CostsCategoryInteract) :
    BaseMvpPresenter<CostsCategoryView>(), CategoryAdapterContract.AdapterPresenter,
    CategoryAdapterContract.CategoryItemPresenter {

    private val categoryList: ArrayList<CategoryEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        categoryList.addAll(
            costsCategoryInteract.getCategoryList()
                .filter { !it.isDynamic }
        )
        loadCostsList()
    }

    override fun getItemsCount(): Int = categoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = categoryList[position]
        view.setIcon(item.iconId)
        view.setText(item.titleId)
    }

    override fun onCategoryItemClicked(position: Int) {
        viewState.navigateToInputCostsAmount(categoryList[position].id)
    }

    override fun onCategoryItemLongClick(position: Int): Boolean = false

    private fun loadCostsList() {
        costsCategoryInteract.loadCostsList()
            .subscribeOn(Schedulers.io())
            .map { list ->
                mapCostsListToCategoryList(list)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                },
                onError = {}
            )
            .let { disposables.add(it) }
    }

    private fun mapCostsListToCategoryList(costsList: List<CostsEntity>) {
        categoryList.forEach { category ->
            category.amount = 0f
            costsList
                .filter { costsEntity -> costsEntity.categoryId == category.id }
                .forEach {
                    if (category.id == it.categoryId) {
                        category.rating++
                    }
                }
        }
        categoryList.sortByDescending { it.rating }
        viewState.notifyDataSetChanged()
    }
}