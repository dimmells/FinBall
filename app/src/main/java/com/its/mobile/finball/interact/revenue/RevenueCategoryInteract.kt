package com.its.mobile.finball.interact.revenue

import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.category.RevenueCategoryManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import io.reactivex.Single

class RevenueCategoryInteract(private val revenueCategoryManager: RevenueCategoryManager, private val revenueDBManager: RevenueDBManager, private val subCategoryDBManager: SubCategoryDBManager) {

    fun getCategoryList(): ArrayList<CategoryEntity> = revenueCategoryManager.getRevenueCategoryList()

    fun loadRevenueList(): Single<List<RevenueEntity>> = revenueDBManager.getAll()

    fun loadRevenueSubCategory(parentCategoryId: Int): Single<List<SubCategoryEntity>> = subCategoryDBManager.getSubCategoryList(parentCategoryId)

}