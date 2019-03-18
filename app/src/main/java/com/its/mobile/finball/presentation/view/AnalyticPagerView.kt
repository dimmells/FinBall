package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface AnalyticPagerView: BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setPagerPosition(position: Int)

}