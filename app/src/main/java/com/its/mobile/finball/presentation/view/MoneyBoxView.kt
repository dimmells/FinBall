package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MoneyBoxView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setMoneyBoxInvestmentRulesVisible(isVisible: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun statrtRuleViewsAnimation(isVisible: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setMoneyBoxInputEnabled(isEnabled: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showWaitAlert(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun clearMoneyBoxInvestmentInput()
}