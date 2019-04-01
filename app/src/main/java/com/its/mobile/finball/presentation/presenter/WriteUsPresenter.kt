package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.presentation.view.WriteUsView

@InjectViewState
class WriteUsPresenter : BaseMvpPresenter<WriteUsView>() {

    fun onSendButtonClick() {
        viewState.hideKeyboard()
        viewState.sendEmail()
        viewState.popBackStack()
    }
}