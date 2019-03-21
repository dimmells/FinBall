package com.its.mobile.finball.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.its.mobile.finball.data.database.costs.CostsDao
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDao
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryDao
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity

@Database(
        entities = [RevenueEntity::class, CostsEntity::class, SubCategoryEntity::class],
        version = DatabaseConfiguration.DATABASE_VERSION
)
@TypeConverters(TimeConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun revenueDao(): RevenueDao

    abstract fun costsDao(): CostsDao

    abstract fun subCategoryDao(): SubCategoryDao

}