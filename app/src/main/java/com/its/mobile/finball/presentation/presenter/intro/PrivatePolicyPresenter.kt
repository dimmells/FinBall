package com.its.mobile.finball.presentation.presenter.intro

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.PrivatePolicyView

@InjectViewState
class PrivatePolicyPresenter() : BaseMvpPresenter<PrivatePolicyView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setGetStartedButtonEnabled(false)
    }

    fun onCheckBoxClick(isChecked: Boolean) {
        viewState.setGetStartedButtonEnabled(isChecked)
    }

    fun onUserAgreePolicy() {
        viewState.navigateToAuthorization()
    }
}