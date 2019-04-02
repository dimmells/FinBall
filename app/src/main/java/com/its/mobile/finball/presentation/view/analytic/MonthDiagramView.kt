package com.its.mobile.finball.presentation.view.analytic

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.presentation.view.BaseMvpView

interface MonthDiagramView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setRevenueChartData(records: List<RevenueEntity>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCostsChartData(records: List<CostsEntity>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)
}