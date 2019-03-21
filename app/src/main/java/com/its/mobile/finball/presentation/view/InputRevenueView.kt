package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface InputRevenueView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCategoryName(stringResId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCategoryName(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goBack()

}