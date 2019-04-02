package com.its.mobile.finball.presentation.view.analytic

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

interface MoneyBoxView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setMoneyBoxInvestmentRulesVisible(isVisible: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startRuleViewsAnimation(isVisible: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setMoneyBoxInputEnabled(isEnabled: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showWaitAlert(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun clearMoneyBoxInvestmentInput()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setFinIndependencyResult(result: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setPricePerWorkHour(price: String)
}