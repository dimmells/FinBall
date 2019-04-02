package com.its.mobile.finball.interact.analytic

import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager

class RevenueRatingInteract(
    private val revenueDBManager: RevenueDBManager,
    private val revenueCategoryManager: RevenueCategoryManager,
    private val subCategoryDBManager: SubCategoryDBManager
) {

    fun loadRevenueList() = revenueDBManager.getAll()

    fun getRevenueCategories() = revenueCategoryManager.getRevenueCategoryList()

    fun loadRevenueSubCategory(parentCategoryId: Int) = subCategoryDBManager.getSubCategoryList(parentCategoryId)

}