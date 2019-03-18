package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.CategoryEntity
import com.its.mobile.finball.interact.InputRevenueInteract
import com.its.mobile.finball.presentation.view.InputRevenueView

@InjectViewState
class InputRevenuePresenter(private val inputRevenueInteract: InputRevenueInteract): BaseMvpPresenter<InputRevenueView>() {

    private lateinit var category: CategoryEntity

    fun onStart(categoryId: Int) {
        inputRevenueInteract.getCategoryInfo(categoryId)?.let {
            category = it
            viewState.setCategoryName(category.titleId)
        }
    }
}