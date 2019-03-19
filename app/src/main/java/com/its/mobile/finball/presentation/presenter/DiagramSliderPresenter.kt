package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.presentation.view.DiagramSliderView

@InjectViewState
class DiagramSliderPresenter: BaseMvpPresenter<DiagramSliderView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setActiveNavigationPoint()
    }

    fun onPageSelected() { viewState.setActiveNavigationPoint() }
}