package com.its.mobile.finball.presentation.view

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.SkuDetails
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SubscriptionsShopView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPurchaseDialog(sku: SkuDetails, billingClient: BillingClient)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showRetrySnackbar()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLoadingBarVisibility(visible: Boolean)
}