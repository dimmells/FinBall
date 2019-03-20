package com.its.mobile.finball.interact

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity

class MoneyBoxInteract(private val costsDBManager: CostsDBManager) {

    fun insertMoneyBoxInvestment(costsEntity: CostsEntity) = costsDBManager.insert(costsEntity)

    fun notifyAboutUpdate() = costsDBManager.updateOther()
}