package com.its.mobile.finball.data.database.revenue

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.its.mobile.finball.data.database.DatabaseConfiguration
import java.util.*

@Entity(tableName = DatabaseConfiguration.Revenue.TABLE_NAME)
class RevenueEntity (

    @PrimaryKey
    @ColumnInfo(name = DatabaseConfiguration.Revenue.Columns.DATE)
    val date: Date,

    @ColumnInfo(name = DatabaseConfiguration.Revenue.Columns.CATEGORY_ID)
    val categoryId: Int,

    @ColumnInfo(name = DatabaseConfiguration.Revenue.Columns.AMOUNT)
    val amount: Float
)