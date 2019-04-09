package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.SubscriptionsShopModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.SubscriptionsShopPresenter
import dagger.Subcomponent

@Subcomponent(modules = [SubscriptionsShopModule::class])
@ViewScope
interface SubscriptionsShopComponent {

    fun providePresenter(): SubscriptionsShopPresenter
}