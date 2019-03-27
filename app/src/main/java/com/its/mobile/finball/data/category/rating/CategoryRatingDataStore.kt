package com.its.mobile.finball.data.category.rating

import android.content.SharedPreferences

class CategoryRatingDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_REVENUE = "revenue"
        const val KEY_COSTS = "costs"
    }

    fun updateRevenueCategoryRating(categoryId: Int, newRating: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_REVENUE.plus(categoryId), newRating)
        editor.apply()
    }

    fun getRevenueCategoryRating(categoryId: Int): Int = sharedPreferences.getInt(KEY_REVENUE.plus(categoryId), 0)
}