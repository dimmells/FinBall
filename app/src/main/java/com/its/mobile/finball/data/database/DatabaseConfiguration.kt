package com.its.mobile.finball.data.database

object DatabaseConfiguration {

    const val DATABASE_NAME = "finball_db"
    const val DATABASE_VERSION = 1

    object Revenue {

        const val TABLE_NAME = "revenue"

        object Columns {
            const val DATE = "date"
            const val CATEGORY_ID = "category_id"
            const val AMOUNT = "amount"
        }

    }

    object Costs {

        const val TABLE_NAME = "costs"

        object Columns {
            const val DATE = "date"
            const val CATEGORY_ID = "category_id"
            const val AMOUNT = "amount"
        }

    }
}