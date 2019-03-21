package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.RevenueSubCategoryInteract
import com.its.mobile.finball.presentation.presenter.RevenueSubCategoryPresenter
import dagger.Module
import dagger.Provides

@Module
class RevenueSubCategoryModule {

    @Provides
    @ViewScope
    fun provideRevenueSubCategoryInteract(subCategoryDBManager: SubCategoryDBManager): RevenueSubCategoryInteract = RevenueSubCategoryInteract(subCategoryDBManager)

    @Provides
    @ViewScope
    fun provideRevenueSubCategoryPresenter(revenueSubCategoryInteract: RevenueSubCategoryInteract): RevenueSubCategoryPresenter = RevenueSubCategoryPresenter(revenueSubCategoryInteract)
}