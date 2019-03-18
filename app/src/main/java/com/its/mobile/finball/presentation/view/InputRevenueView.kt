package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface InputRevenueView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCategoryName(stringResId: Int)
}