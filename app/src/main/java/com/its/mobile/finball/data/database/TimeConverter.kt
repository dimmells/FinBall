package com.its.mobile.finball.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*

class TimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun toTimestamp(value: Date?): Long? = value?.time

}