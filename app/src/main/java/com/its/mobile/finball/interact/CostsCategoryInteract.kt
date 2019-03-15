package com.its.mobile.finball.interact

import com.its.mobile.finball.data.CategoryEntity
import com.its.mobile.finball.data.CostsCategoryManager

class CostsCategoryInteract(private val costsCategoryManager: CostsCategoryManager) {

    fun getCategoryList(): ArrayList<CategoryEntity> = costsCategoryManager.getCostsCategoryList()
}