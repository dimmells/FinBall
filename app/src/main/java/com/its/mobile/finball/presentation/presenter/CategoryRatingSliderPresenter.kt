package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.presentation.view.CategoryRatingSliderView

@InjectViewState
class CategoryRatingSliderPresenter : BaseMvpPresenter<CategoryRatingSliderView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setActiveNavigationPoint()
    }

    fun onPageSelected() { viewState.setActiveNavigationPoint() }
}