package com.its.mobile.finball.di.module

import android.content.Context
import android.content.SharedPreferences
import com.its.mobile.finball.data.category.rating.CategoryRatingDataStore
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class CategoryRatingDSModule {

    @Provides
    @ApplicationScope
    @CategoryRatingSPQuaifier
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("CategoryRating", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideCategoryRatingDataStore(@CategoryRatingSPQuaifier sharedPreferences: SharedPreferences): CategoryRatingDataStore =
        CategoryRatingDataStore(sharedPreferences)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CategoryRatingSPQuaifier