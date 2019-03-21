package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.InputRevenueInteract
import com.its.mobile.finball.presentation.view.InputRevenueView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class InputRevenuePresenter(private val inputRevenueInteract: InputRevenueInteract) :
    BaseMvpPresenter<InputRevenueView>() {

    private lateinit var category: CategoryEntity

    fun onStart(categoryId: Int) {
        loadCategoryInfo(categoryId)
    }

    private fun loadCategoryInfo(categoryId: Int) {
        inputRevenueInteract.getCategoryInfo(categoryId)?.let {
            category = it
            viewState.setCategoryName(category.titleId)
        }
    }

    fun onSaveClick(amount: Float) {
        inputRevenueInteract.saveRevenue(RevenueEntity(Calendar.getInstance().time, category.id, amount))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                viewState.showToast("Error: ${throwable.localizedMessage}")
            }
            .doOnSuccess { viewState.goBack() }
            .subscribe()
            .let { disposables.add(it) }
    }
}