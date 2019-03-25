package com.its.mobile.finball.interact

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import io.reactivex.Single
import java.util.*

class MoneyBoxInteract(private val costsDBManager: CostsDBManager, private val revenueDBManager: RevenueDBManager) {

    fun insertCosts(costsEntity: CostsEntity) = costsDBManager.insert(costsEntity)

    fun notifyAboutUpdate() = costsDBManager.notifyAboutUpdate()

    fun getRevenueBetweenDates(from: Date, to: Date): Single<List<Float>> = revenueDBManager.getAmountBetweenDates(from, to)

    fun getCostsByCategory(categoryId: Int): Single<List<CostsEntity>> = costsDBManager.getByCategory(categoryId)

    fun getCostsInMonth(month: Calendar): Single<List<CostsEntity>> = costsDBManager.getInMonth(month)

}