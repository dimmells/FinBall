package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.CategoryEntity
import com.its.mobile.finball.interact.InputCostsInteract
import com.its.mobile.finball.interact.InputRevenueInteract
import com.its.mobile.finball.presentation.view.InputCostsView
import com.its.mobile.finball.presentation.view.InputRevenueView

@InjectViewState
class InputCostsPresenter(private val inputCostsInteract: InputCostsInteract): BaseMvpPresenter<InputCostsView>() {

    private lateinit var category: CategoryEntity

    fun onStart(categoryId: Int) {
        inputCostsInteract.getCategoryInfo(categoryId)?.let {
            category = it
            viewState.setCategoryName(category.titleId)
        }
    }
}