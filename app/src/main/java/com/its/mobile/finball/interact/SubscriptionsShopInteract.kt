package com.its.mobile.finball.interact

import com.android.billingclient.api.SkuDetails
import com.its.mobile.finball.data.billing.BillingManager
import io.reactivex.Single

class SubscriptionsShopInteract(val billingManager: BillingManager) {

    fun loadPurchasesDetails(): Single<List<SkuDetails>> = billingManager.getInAppPurchasesDetails()


}