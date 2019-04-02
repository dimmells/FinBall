package com.its.mobile.finball.interact.costs

import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import io.reactivex.Single

class CostsCategoryInteract(private val costsCategoryManager: CostsCategoryManager, private val costsDBManager: CostsDBManager) {

    fun getCategoryList(): ArrayList<CategoryEntity> = costsCategoryManager.getCostsCategoryList()

    fun loadCostsList(): Single<List<CostsEntity>> = costsDBManager.getAll()
}