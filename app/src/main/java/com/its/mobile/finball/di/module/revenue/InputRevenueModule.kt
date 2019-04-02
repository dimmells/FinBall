package com.its.mobile.finball.di.module.revenue

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.revenue.InputRevenueInteract
import com.its.mobile.finball.presentation.presenter.revenue.InputRevenuePresenter
import dagger.Module
import dagger.Provides

@Module
class InputRevenueModule {

    @Provides
    @ViewScope
    fun provideInputRevenueInteract(
        revenueCategoryManager: RevenueCategoryManager,
        revenueDBManager: RevenueDBManager,
        subCategoryDBManager: SubCategoryDBManager
    ): InputRevenueInteract =
        InputRevenueInteract(
            revenueCategoryManager,
            revenueDBManager,
            subCategoryDBManager
        )

    @Provides
    @ViewScope
    fun provideInputRevenuePrersenter(inputRevenueInteract: InputRevenueInteract): InputRevenuePresenter =
        InputRevenuePresenter(inputRevenueInteract)
}