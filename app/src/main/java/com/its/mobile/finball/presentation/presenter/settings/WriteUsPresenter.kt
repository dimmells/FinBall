package com.its.mobile.finball.presentation.presenter.settings

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.settings.WriteUsView

@InjectViewState
class WriteUsPresenter : BaseMvpPresenter<WriteUsView>() {

    fun onSendButtonClick() {
        viewState.hideKeyboard()
        viewState.sendEmail()
        viewState.popBackStack()
    }
}