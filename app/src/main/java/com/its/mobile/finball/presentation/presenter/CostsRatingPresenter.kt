package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.CostsRatingInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.view.CostsRatingView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class CostsRatingPresenter(private val costsRatingInteract: CostsRatingInteract) : BaseMvpPresenter<CostsRatingView>(),
    CategoryAdapterContract.AdapterPresenter, CategoryAdapterContract.CategoryItemPresenter {

    private val costsCategoryList: ArrayList<CategoryEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        insert(CostsEntity(Date(969656400000), 1, 25.90f))
        insert(CostsEntity(Date(969742700000), 3, 50f))
        insert(CostsEntity(Date(969742800000), 4, 10.90f))
        insert(CostsEntity(Date(969915600000), 3, 20f))
        insert(CostsEntity(Date(970002000000), 1, 150.40f))
        insert(CostsEntity(Date(970088400000), 3, 205f))
        insert(CostsEntity(Date(970174800000), 1, 540.45f))
        //DELETE!!!!!!!!!!!!!!!!!!!!!
        costsCategoryList.addAll(costsRatingInteract.getCostsCategories())
        loadRevenueList()
    }

    override fun getItemsCount(): Int = costsCategoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = costsCategoryList[position]
        view.setIcon(item.iconId)
        view.setText(item.titleId)
        view.setAmount(item.amount)
    }

    override fun onCategoryItemClicked(position: Int) {}

    private fun loadRevenueList() {
        costsRatingInteract.loadCostsList()
            .subscribeOn(Schedulers.io())
            .map { list ->
                mapRevenueListToCategoryList(list)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { viewState.notifyDataSetChanged() },
                onError = { viewState.showToast(it.localizedMessage) })
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
//            .filter { it.amount > 0f }
            .sortedByDescending { list -> list.amount }
        costsCategoryList.clear()
        costsCategoryList.addAll(newList)
    }

    private fun insert(costsEntity: CostsEntity) {
        costsRatingInteract.insertTest(costsEntity)
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