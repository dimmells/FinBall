package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RevenueCategoryView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToInputRevenueAmount(categoryId: Int)
}