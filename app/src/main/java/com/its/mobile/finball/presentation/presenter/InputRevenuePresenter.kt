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

    private var categoryId = 0

    fun onStart(categoryId: Int) {
        this.categoryId = categoryId
        if (!loadCategoryInfo(categoryId)) {
            inputRevenueInteract.getSubCategoryInfo(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { viewState.setCategoryName(it.title) },
                    { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
        }
    }

    private fun loadCategoryInfo(categoryId: Int): Boolean {
        inputRevenueInteract.getCategoryInfo(categoryId)?.let {
            viewState.setCategoryName(it.titleId)
            return true
        }
        return false
    }

    fun onSaveClick(input: String) {
        if (input.isEmpty())  {
            viewState.showToast("Enter amount")
            return
        }
        val amount = input.toFloat()
        inputRevenueInteract.saveRevenue(RevenueEntity(Calendar.getInstance().time, categoryId, amount))
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