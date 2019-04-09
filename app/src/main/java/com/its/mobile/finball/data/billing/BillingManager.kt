package com.its.mobile.finball.data.billing

import android.content.Context
import android.widget.Toast
import com.android.billingclient.api.*
import io.reactivex.Completable
import io.reactivex.Single
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.its.mobile.finball.data.user.UserManager
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams


class BillingManager(private val context: Context, private val userManager: UserManager) : PurchasesUpdatedListener {

    val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(this)
        .build()

    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {
        if (responseCode == BillingClient.BillingResponse.OK) {
            purchases?.forEach { purchase ->
                Toast.makeText(context, purchase.originalJson, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateServiceConnection(): Completable = Completable.create { emitter ->
        if (billingClient.isReady) {
            emitter.onComplete()
        } else {
            billingClient.startConnection(object : BillingClientStateListener {

                override fun onBillingServiceDisconnected() {}

                override fun onBillingSetupFinished(responseCode: Int) {
                    if (responseCode == BillingClient.BillingResponse.OK) {
                        emitter.onComplete()
                    } else
                        emitter.onError(BillingException("code = $responseCode"))
                }
            })
        }
    }

    fun getInAppPurchasesDetails(): Single<List<SkuDetails>> = updateServiceConnection()
        .onErrorComplete()
        .andThen(loadSkuDetailsList().onErrorReturnItem(ArrayList()))

    private fun loadSkuDetailsList() = Single.create<List<SkuDetails>> { emitter ->
        val skuList: ArrayList<String> = ArrayList()
        skuList.add("fin_ball_three_month_subscribe")
        val params = SkuDetailsParams.newBuilder()
            .setSkusList(skuList)
            .setType(BillingClient.SkuType.SUBS)
            .build()

        billingClient.querySkuDetailsAsync(params) { responseCode, skuDetailsList ->
            if (responseCode == BillingClient.BillingResponse.OK && skuDetailsList != null)
                emitter.onSuccess(skuDetailsList)
            else
                emitter.onError(BillingException("code = $responseCode"))
        }
    }

    fun querySubscriptions(): Single<List<Purchase>> =
        Single.fromCallable {
            val purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.SUBS)

            if (purchasesResult.responseCode != BillingClient.BillingResponse.OK) {
//                println(purchasesResult.purchasesList[0])
//                Toast.makeText(context, purchasesResult.purchasesList.size.toString(), Toast.LENGTH_SHORT).show()
            }
            onPurchasesUpdated(BillingClient.BillingResponse.OK, purchasesResult.purchasesList)
            purchasesResult.purchasesList
        }

}