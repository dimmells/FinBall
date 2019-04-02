package com.its.mobile.finball.di.module.revenue

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.revenue.RevenueCategoryInteract
import com.its.mobile.finball.presentation.presenter.revenue.RevenueCategoryPresenter
import dagger.Module
import dagger.Provides

@Module
class RevenueCategoryModule {

    @Provides
    @ViewScope
    fun provideRevenueCategoryInteract(
        revenueCategoryManager: RevenueCategoryManager,
        revenueDBManager: RevenueDBManager,
        subCategoryDBManager: SubCategoryDBManager
    ): RevenueCategoryInteract =
        RevenueCategoryInteract(
            revenueCategoryManager,
            revenueDBManager,
            subCategoryDBManager
        )

    @Provides
    @ViewScope
    fun provideRevenueCategoryPresenter(revenueCategoryInteract: RevenueCategoryInteract): RevenueCategoryPresenter =
        RevenueCategoryPresenter(revenueCategoryInteract)
}