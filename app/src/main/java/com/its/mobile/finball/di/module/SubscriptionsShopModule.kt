package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.billing.BillingManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.SubscriptionsShopInteract
import com.its.mobile.finball.presentation.presenter.SubscriptionsShopPresenter
import dagger.Module
import dagger.Provides

@Module
class SubscriptionsShopModule {

    @Provides
    @ViewScope
    fun provideSubscriptionsShopInteract(billingManager: BillingManager): SubscriptionsShopInteract = SubscriptionsShopInteract(billingManager)

    @Provides
    @ViewScope
    fun provideSubscriptionsShopPresenter(subscriptionsShopInteract: SubscriptionsShopInteract): SubscriptionsShopPresenter = SubscriptionsShopPresenter(subscriptionsShopInteract)
}