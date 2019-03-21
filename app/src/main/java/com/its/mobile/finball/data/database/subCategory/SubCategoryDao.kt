package com.its.mobile.finball.data.database.subCategory

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.its.mobile.finball.data.database.DatabaseConfiguration
import io.reactivex.Single

@Dao
interface SubCategoryDao {

    @Query("SELECT * FROM ${DatabaseConfiguration.SubCategory.TABLE_NAME}")
    fun getAll(): Single<List<SubCategoryEntity>>

    @Query("SELECT * FROM ${DatabaseConfiguration.SubCategory.TABLE_NAME} WHERE ${DatabaseConfiguration.SubCategory.Columns.CATEGORY_ID} = :subCategoryId")
    fun getSubCategory(subCategoryId: Int): Single<SubCategoryEntity>

    @Query("SELECT * FROM ${DatabaseConfiguration.SubCategory.TABLE_NAME} WHERE ${DatabaseConfiguration.SubCategory.Columns.PARENT_CATEGORY_ID} = :parentCategoryId")
    fun getSubCategoryList(parentCategoryId: Int): Single<List<SubCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subCategoryEntity: SubCategoryEntity): Long

    @Query("DELETE FROM ${DatabaseConfiguration.SubCategory.TABLE_NAME}")
    fun clear()
}