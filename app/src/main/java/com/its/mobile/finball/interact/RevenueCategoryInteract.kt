package com.its.mobile.finball.interact

import com.its.mobile.finball.data.CategoryEntity
import com.its.mobile.finball.data.RevenueCategoryManager

class RevenueCategoryInteract(private val revenueCategoryManager: RevenueCategoryManager) {

    fun getCategoryList(): ArrayList<CategoryEntity> = revenueCategoryManager.getRevenueCategoryList()
}