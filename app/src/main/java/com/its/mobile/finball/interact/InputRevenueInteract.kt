package com.its.mobile.finball.interact

import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import io.reactivex.Single
import java.util.*

class InputRevenueInteract(
    private val revenueCategoryManager: RevenueCategoryManager,
    private val revenueDBManager: RevenueDBManager,
    private val subCategoryDBManager: SubCategoryDBManager
) {

    fun getCategoryInfo(categoryId: Int): CategoryEntity? = revenueCategoryManager.getRevenueCategory(categoryId)

    fun getSubCategoryInfo(subCategoryId: Int): Single<SubCategoryEntity> = subCategoryDBManager.getSubCategory(subCategoryId)

    fun saveRevenue(revenueEntity: RevenueEntity): Single<Long> = revenueDBManager.insert(revenueEntity)

}