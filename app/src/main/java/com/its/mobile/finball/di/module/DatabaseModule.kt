package com.its.mobile.finball.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.its.mobile.finball.data.database.ApplicationDatabase
import com.its.mobile.finball.data.database.DatabaseConfiguration
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsDao
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueDao
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryDao
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(context: Context): ApplicationDatabase = Room
        .databaseBuilder(context, ApplicationDatabase::class.java, DatabaseConfiguration.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @ApplicationScope
    fun provideRevenueDao(database: ApplicationDatabase): RevenueDao = database.revenueDao()

    @Provides
    @ApplicationScope
    fun provideRevenueDBManager(revenueDao: RevenueDao): RevenueDBManager = RevenueDBManager(revenueDao)

    @Provides
    @ApplicationScope
    fun provideCostsDao(database: ApplicationDatabase): CostsDao = database.costsDao()

    @Provides
    @ApplicationScope
    fun provideCostsDBManager(costsDao: CostsDao): CostsDBManager = CostsDBManager(costsDao)

    @Provides
    @ApplicationScope
    fun provideSubCategoryDao(database: ApplicationDatabase): SubCategoryDao = database.subCategoryDao()

    @Provides
    @ApplicationScope
    fun provideSubCategoryDBManager(subCategoryDao: SubCategoryDao): SubCategoryDBManager = SubCategoryDBManager(subCategoryDao)
}