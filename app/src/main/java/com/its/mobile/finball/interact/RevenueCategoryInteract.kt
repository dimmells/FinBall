package com.its.mobile.finball.interact

import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.category.RevenueCategoryManager

class RevenueCategoryInteract(private val revenueCategoryManager: RevenueCategoryManager) {

    fun getCategoryList(): ArrayList<CategoryEntity> = revenueCategoryManager.getRevenueCategoryList()
}