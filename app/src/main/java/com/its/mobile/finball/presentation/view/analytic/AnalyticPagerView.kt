package com.its.mobile.finball.presentation.view.analytic

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface AnalyticPagerView: BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setPagerPosition(position: Int)

}