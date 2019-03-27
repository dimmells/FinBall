package com.its.mobile.finball.interact

import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import io.reactivex.Single
import java.util.*

class InputCostsInteract(private val costsCategoryManager: CostsCategoryManager, private val costsDBManager: CostsDBManager) {

    fun getCategoryInfo(categoryId: Int): CategoryEntity? = costsCategoryManager.getCostsCategory(categoryId)

    fun saveRevenue(costsEntity: CostsEntity): Single<Long> = costsDBManager.insert(costsEntity)

}