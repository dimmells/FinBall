package com.its.mobile.finball.data.database.subCategory

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.its.mobile.finball.data.database.DatabaseConfiguration

@Entity(tableName = DatabaseConfiguration.SubCategory.TABLE_NAME)
class SubCategoryEntity(

    @PrimaryKey
    @ColumnInfo(name = DatabaseConfiguration.SubCategory.Columns.CATEGORY_ID)
    val id: Int,

    @ColumnInfo(name = DatabaseConfiguration.SubCategory.Columns.PARENT_CATEGORY_ID)
    val parentCategory: Int,

    @ColumnInfo(name = DatabaseConfiguration.SubCategory.Columns.TITLE)
    val title: String
)