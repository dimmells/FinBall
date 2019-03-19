package com.its.mobile.finball.interact

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity

class RevenueRatingInteract(
    private val revenueDBManager: RevenueDBManager,
    private val revenueCategoryManager: RevenueCategoryManager
) {

    fun loadRevenueList() = revenueDBManager.getAll()

    fun getRevenueCategories() = revenueCategoryManager.getRevenueCategoryList()

    fun insertTest(revenueEntity: RevenueEntity) = revenueDBManager.insert(revenueEntity)

}