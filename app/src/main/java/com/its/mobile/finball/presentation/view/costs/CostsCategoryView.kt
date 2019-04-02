package com.its.mobile.finball.presentation.view.costs

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface CostsCategoryView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToInputCostsAmount(categoryId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataSetChanged()
}