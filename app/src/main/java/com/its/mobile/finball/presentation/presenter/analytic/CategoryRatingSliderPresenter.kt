package com.its.mobile.finball.presentation.presenter.analytic

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.analytic.CategoryRatingSliderView

@InjectViewState
class CategoryRatingSliderPresenter : BaseMvpPresenter<CategoryRatingSliderView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setActiveNavigationPoint()
    }

    fun onPageSelected() { viewState.setActiveNavigationPoint() }
}