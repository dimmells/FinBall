package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.mikephil.charting.data.PieEntry

interface MenuView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setQuote(quote: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setAuthor(author: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setupChart(entries: MutableList<PieEntry>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToRevenue()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToCosts()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToAnalytic()
}