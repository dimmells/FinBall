package com.its.mobile.finball.presentation.view.costs

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface InputCostsView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCategoryName(stringResId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goBack()
}