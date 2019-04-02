package com.its.mobile.finball.presentation.presenter.analytic

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.analytic.CostsRatingInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.analytic.CostsRatingView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class CostsRatingPresenter(private val costsRatingInteract: CostsRatingInteract) : BaseMvpPresenter<CostsRatingView>(),
    CategoryAdapterContract.AdapterPresenter, CategoryAdapterContract.CategoryItemPresenter {

    private val costsCategoryList: ArrayList<CategoryEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        costsCategoryList.addAll(costsRatingInteract.getCostsCategories())
        loadCostsList()
    }

    override fun getItemsCount(): Int = costsCategoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = costsCategoryList[position]
        view.setIcon(item.iconId)
        view.setText(item.titleId)
        view.setAmount(item.amount)
    }

    override fun onCategoryItemClicked(position: Int) {}

    override fun onCategoryItemLongClick(position: Int): Boolean = false

    private fun loadCostsList() {
        costsRatingInteract.loadCostsList()
            .subscribeOn(Schedulers.io())
            .map { single ->
                subscribeOnSingle(single)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let { disposables.add(it) }
    }

    private fun subscribeOnSingle(single: Single<List<CostsEntity>>) {
        single
            .subscribeOn(Schedulers.io())
            .map { list ->
                mapRevenueListToCategoryList(list)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.notifyDataSetChanged() },
                { viewState.showToast(it.localizedMessage) })
            .let { disposables.add(it) }
    }

    private fun mapRevenueListToCategoryList(costsList: List<CostsEntity>) {
        costsCategoryList.forEach { category ->
            category.amount = 0f
            costsList
                .sortedBy { list -> list.categoryId == category.id }
                .forEach {
                    if (category.id == it.categoryId) {
                        category.amount += it.amount
                    }
                }
        }
        val newList = costsCategoryList
            .sortedByDescending { list -> list.amount }
        costsCategoryList.clear()
        costsCategoryList.addAll(newList)
    }
}