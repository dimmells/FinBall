package com.its.mobile.finball.interact.analytic

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity

class CostsRatingInteract(
    private val costsDBManager: CostsDBManager,
    private val costsCategoryManager: CostsCategoryManager
) {

    fun loadCostsList() = costsDBManager.costsListObservable

    fun getCostsCategories() = costsCategoryManager.getCostsCategoryList()

    fun insertTest(costsEntity: CostsEntity) = costsDBManager.insert(costsEntity)

}