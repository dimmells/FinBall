package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.RevenueCategoryInteract
import com.its.mobile.finball.presentation.presenter.RevenueCategoryPresenter
import dagger.Module
import dagger.Provides

@Module
class RevenueCategoryModule {

    @Provides
    @ViewScope
    fun provideRevenueCategoryInteract(revenueCategoryManager: RevenueCategoryManager): RevenueCategoryInteract = RevenueCategoryInteract(revenueCategoryManager)

    @Provides
    @ViewScope
    fun provideRevenueCategoryPresenter(revenueCategoryInteract: RevenueCategoryInteract): RevenueCategoryPresenter = RevenueCategoryPresenter(revenueCategoryInteract)
}