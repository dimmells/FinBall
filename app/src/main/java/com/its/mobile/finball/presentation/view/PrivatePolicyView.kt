package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface PrivatePolicyView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setGetStartedButtonEnabled(enabled: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToAuthorization()

}

