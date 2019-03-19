package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.RevenueRatingInteract
import com.its.mobile.finball.presentation.presenter.RevenueRatingPresenter
import dagger.Module
import dagger.Provides

@Module
class RevenueRatingModule {

    @Provides
    @ViewScope
    fun provideRevenueRatingInteract(revenueDBManager: RevenueDBManager, revenueCategoryManager: RevenueCategoryManager): RevenueRatingInteract = RevenueRatingInteract(revenueDBManager, revenueCategoryManager)

    @Provides
    @ViewScope
    fun provideRevenueRatingPresenter(revenueRatingInteract: RevenueRatingInteract): RevenueRatingPresenter = RevenueRatingPresenter(revenueRatingInteract)
}