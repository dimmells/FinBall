package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.interact.MoneyBoxInteract
import com.its.mobile.finball.presentation.view.MoneyBoxView

@InjectViewState
class MoneyBoxPresenter(private val moneyBoxInteract: MoneyBoxInteract): BaseMvpPresenter<MoneyBoxView>() {
}