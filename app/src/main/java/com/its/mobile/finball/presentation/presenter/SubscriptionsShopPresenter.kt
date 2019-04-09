package com.its.mobile.finball.presentation.presenter

import com.android.billingclient.api.SkuDetails
import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.interact.SubscriptionsShopInteract
import com.its.mobile.finball.presentation.adapter.ShopAdapterContracts
import com.its.mobile.finball.presentation.view.SubscriptionsShopView
import com.its.mobile.finball.ui.viewholder.ShopPurchaseItemViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class SubscriptionsShopPresenter(private val subscriptionsShopInteract: SubscriptionsShopInteract) :
    BaseMvpPresenter<SubscriptionsShopView>(),
    ShopAdapterContracts.AdapterPresenter,
    ShopAdapterContracts.PurchaseItemPresenter {

    private val items: ArrayList<SkuDetails> = ArrayList()

    override fun onFirstViewAttach() {
        loadShopItems()
    }

    override fun getItemsCount(): Int = items.size

    override fun onBindShopPurchaseItemView(viewHolder: ShopPurchaseItemViewHolder, position: Int) {
        viewHolder.setPrice(items[position].price + " " + items[position].priceCurrencyCode)
    }

    override fun onShopPurchaseItemClicked(position: Int) {
        viewState.showPurchaseDialog(items[position], subscriptionsShopInteract.billingManager.billingClient)
    }

    private fun loadShopItems() {
        viewState.setLoadingBarVisibility(true)
        subscriptionsShopInteract.loadPurchasesDetails()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    viewState.setLoadingBarVisibility(false)
                    items.clear()
                    items.addAll(it)
                    viewState.notifyDataChanged()
                },
                onError = {
                    viewState.setLoadingBarVisibility(false)
                    viewState.showRetrySnackbar()
                }
            )
            .addTo(disposables)
    }

    fun onRetryClicked() {
        loadShopItems()
    }
}