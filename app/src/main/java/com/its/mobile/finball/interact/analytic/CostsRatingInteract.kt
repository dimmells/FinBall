package com.its.mobile.finball.interact.analytic

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager

class CostsRatingInteract(
    private val costsDBManager: CostsDBManager,
    private val costsCategoryManager: CostsCategoryManager
) {

    fun loadCostsList() = costsDBManager.costsListObservable

    fun getCostsCategories() = costsCategoryManager.getCostsCategoryList()

}