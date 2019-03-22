package com.its.mobile.finball.interact

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import io.reactivex.Single
import java.util.*

class MoneyBoxInteract(private val costsDBManager: CostsDBManager, private val revenueDBManager: RevenueDBManager) {

    fun insertCosts(costsEntity: CostsEntity) = costsDBManager.insert(costsEntity)

    fun notifyAboutUpdate() = costsDBManager.notifyAboutUpdate()

    fun getBetweenDates(from: Date, to: Date): Single<List<Float>> = revenueDBManager.getAmountBetweenDates(from, to)

    fun getByCategory(categoryId: Int) = costsDBManager.getByCategory(categoryId)

}