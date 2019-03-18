package com.its.mobile.finball.interact

import com.its.mobile.finball.data.CategoryEntity
import com.its.mobile.finball.data.CostsCategoryManager
import com.its.mobile.finball.data.RevenueCategoryManager

class InputCostsInteract(private val costsCategoryManager: CostsCategoryManager) {

    fun getCategoryInfo(categoryId: Int): CategoryEntity? = costsCategoryManager.getCostsCategory(categoryId)
}