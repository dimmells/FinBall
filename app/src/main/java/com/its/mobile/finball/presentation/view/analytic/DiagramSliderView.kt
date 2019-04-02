package com.its.mobile.finball.presentation.view.analytic

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface DiagramSliderView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setActiveNavigationPoint()
}