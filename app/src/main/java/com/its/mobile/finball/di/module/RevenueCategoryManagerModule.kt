package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.category.rating.CategoryRatingDataStore
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RevenueCategoryManagerModule {

    @Provides
    @ApplicationScope
    fun provideRevenueCategoryManager(categoryRatingDataStore: CategoryRatingDataStore): RevenueCategoryManager =
        RevenueCategoryManager(categoryRatingDataStore)
}