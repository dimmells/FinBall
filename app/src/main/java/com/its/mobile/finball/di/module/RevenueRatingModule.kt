package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.RevenueRatingInteract
import com.its.mobile.finball.presentation.presenter.RevenueRatingPresenter
import dagger.Module
import dagger.Provides

@Module
class RevenueRatingModule {

    @Provides
    @ViewScope
    fun provideRevenueRatingInteract(
        revenueDBManager: RevenueDBManager,
        revenueCategoryManager: RevenueCategoryManager,
        subCategoryDBManager: SubCategoryDBManager
    ): RevenueRatingInteract = RevenueRatingInteract(revenueDBManager, revenueCategoryManager, subCategoryDBManager)

    @Provides
    @ViewScope
    fun provideRevenueRatingPresenter(revenueRatingInteract: RevenueRatingInteract): RevenueRatingPresenter =
        RevenueRatingPresenter(revenueRatingInteract)
}