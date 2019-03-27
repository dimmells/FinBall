package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface CostsCategoryView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToInputCostsAmount(categoryId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataSetChanged()
}