package com.its.mobile.finball.interact

import com.its.mobile.finball.data.CategoryEntity
import com.its.mobile.finball.data.RevenueCategoryManager

class InputRevenueInteract(private val revenueCategoryManager: RevenueCategoryManager) {

    fun getCategoryInfo(categoryId: Int): CategoryEntity? = revenueCategoryManager.getRevenueCategory(categoryId)
}