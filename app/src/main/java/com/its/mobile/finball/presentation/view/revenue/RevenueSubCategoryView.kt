package com.its.mobile.finball.presentation.view.revenue

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface RevenueSubCategoryView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showDialog()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeDialog()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataSetChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToInputRevenue(subCategoryId: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showRemoveSubCategoryDialog(name: String, position: Int)
}