package com.its.mobile.finball.presentation.view.settings

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface WriteUsView: BaseMvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideKeyboard()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun sendEmail()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun popBackStack()
}