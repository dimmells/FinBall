package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CostsCategoryManagerModule {

    @Provides
    @ApplicationScope
    fun provideRevenueCategoryManager(): CostsCategoryManager =
        CostsCategoryManager()
}