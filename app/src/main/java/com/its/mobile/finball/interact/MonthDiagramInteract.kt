package com.its.mobile.finball.interact

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager

class MonthDiagramInteract(private val revenueDBManager: RevenueDBManager, private val costsDBManager: CostsDBManager) {

    fun loadRevenueList() = revenueDBManager.revenueListObservable

    fun loadCostsList() = costsDBManager.costsListObservable
}