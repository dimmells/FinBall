package com.its.mobile.finball.interact

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import io.reactivex.Single

class YearDiagramInteract(private val revenueDBManager: RevenueDBManager, private val costsDBManager: CostsDBManager) {

    fun loadRevenue(): Single<List<RevenueEntity>> = revenueDBManager.getAll()

    fun loadCosts(): Single<List<CostsEntity>> = costsDBManager.getAll()
}