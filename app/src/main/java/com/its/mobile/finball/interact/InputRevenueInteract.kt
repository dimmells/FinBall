package com.its.mobile.finball.interact

import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import io.reactivex.Single
import java.util.*

class InputRevenueInteract(
    private val revenueCategoryManager: RevenueCategoryManager,
    private val revenueDBManager: RevenueDBManager
) {

    fun getCategoryInfo(categoryId: Int): CategoryEntity? = revenueCategoryManager.getRevenueCategory(categoryId)

    fun saveRevenue(revenueEntity: RevenueEntity): Single<Long> = revenueDBManager.insert(revenueEntity)

    fun getAll(): Single<List<RevenueEntity>> = revenueDBManager.getAll()

    fun getByDay(day: Date): Single<List<RevenueEntity>> = revenueDBManager.getByDay(day)
}