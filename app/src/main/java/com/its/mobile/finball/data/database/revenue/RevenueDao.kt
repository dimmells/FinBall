package com.its.mobile.finball.data.database.revenue

import android.arch.persistence.room.*
import io.reactivex.Single
import com.its.mobile.finball.data.database.DatabaseConfiguration
import java.util.*

@Dao
interface RevenueDao {

    @Query("SELECT * FROM ${DatabaseConfiguration.Revenue.TABLE_NAME}")
    fun getAll(): Single<List<RevenueEntity>>

    @Query("SELECT * FROM ${DatabaseConfiguration.Revenue.TABLE_NAME} WHERE ${DatabaseConfiguration.Revenue.Columns.DATE} BETWEEN :from AND :to")
    fun getBetweenDates(from: Date, to: Date): Single<List<RevenueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(revenueEntity: RevenueEntity): Long

    @Query("DELETE FROM ${DatabaseConfiguration.Revenue.TABLE_NAME}")
    fun clear()
}