package com.its.mobile.finball.data.database.costs

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.its.mobile.finball.data.database.DatabaseConfiguration
import java.util.*

@Entity(tableName = DatabaseConfiguration.Costs.TABLE_NAME)
class CostsEntity (

    @PrimaryKey()
    @ColumnInfo(name = DatabaseConfiguration.Costs.Columns.DATE)
    val date: Date,

    @ColumnInfo(name = DatabaseConfiguration.Costs.Columns.CATEGORY_ID)
    val categoryId: Int,

    @ColumnInfo(name = DatabaseConfiguration.Costs.Columns.AMOUNT)
    val amount: Float
)