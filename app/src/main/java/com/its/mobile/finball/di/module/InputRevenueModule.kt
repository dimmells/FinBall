package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.InputRevenueInteract
import com.its.mobile.finball.presentation.presenter.InputRevenuePresenter
import dagger.Module
import dagger.Provides

@Module
class InputRevenueModule {

    @Provides
    @ViewScope
    fun provideInputRevenueInteract(revenueCategoryManager: RevenueCategoryManager, revenueDBManager: RevenueDBManager): InputRevenueInteract = InputRevenueInteract(revenueCategoryManager, revenueDBManager)

    @Provides
    @ViewScope
    fun provideInputRevenuePrersenter(inputRevenueInteract: InputRevenueInteract): InputRevenuePresenter = InputRevenuePresenter(inputRevenueInteract)
}