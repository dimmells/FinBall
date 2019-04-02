package com.its.mobile.finball.presentation.view.analytic

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.presentation.view.BaseMvpView

interface YearDiagramView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setChartData(records: List<RevenueEntity>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)
}