package com.its.mobile.finball.data.database.costs

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.its.mobile.finball.data.database.DatabaseConfiguration
import io.reactivex.Single
import java.util.*

@Dao
interface CostsDao {

    @Query("SELECT * FROM ${DatabaseConfiguration.Costs.TABLE_NAME}")
    fun getAll(): Single<List<CostsEntity>>

    @Query("SELECT * FROM ${DatabaseConfiguration.Costs.TABLE_NAME} WHERE ${DatabaseConfiguration.Costs.Columns.DATE} BETWEEN :from AND :to")
    fun getBetweenDates(from: Date, to: Date): Single<List<CostsEntity>>

    @Query("SELECT * FROM ${DatabaseConfiguration.Costs.TABLE_NAME} WHERE ${DatabaseConfiguration.Costs.Columns.CATEGORY_ID} = :categoryId")
    fun getByCategory(categoryId: Int): Single<List<CostsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(costsEntity: CostsEntity): Long

    @Query("DELETE FROM ${DatabaseConfiguration.Costs.TABLE_NAME}")
    fun clear()
}