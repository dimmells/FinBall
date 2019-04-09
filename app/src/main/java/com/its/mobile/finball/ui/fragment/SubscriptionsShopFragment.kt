package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.SkuDetails
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.SubscriptionsShopModule
import com.its.mobile.finball.presentation.presenter.SubscriptionsShopPresenter
import com.its.mobile.finball.presentation.view.SubscriptionsShopView
import com.its.mobile.finball.ui.adapter.ShopAdapter
import com.its.mobile.finball.ui.element.ShopItemDecorator
import com.its.mobile.finball.ui.utils.getDP
import kotlinx.android.synthetic.main.fragment_subscriptions_shop.*

class SubscriptionsShopFragment: BaseFragment(), SubscriptionsShopView {

    companion object {
        fun newInstance() = SubscriptionsShopFragment()
    }

    @InjectPresenter
    lateinit var presenter: SubscriptionsShopPresenter

    private var isSnackbarInQueue = false


    @ProvidePresenter
    fun providePresenter(): SubscriptionsShopPresenter = ApplicationLoader.applicationComponent
        .subscriptionsShopComponent(SubscriptionsShopModule())
        .providePresenter()


    override fun getAnalyticsWindowKey(): String? = "likes_shop"


    private lateinit var shopAdapter: ShopAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_subscriptions_shop, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopAdapter = ShopAdapter(presenter, presenter)
        with(recycler_view_subscriptions_shop) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = shopAdapter
            setHasFixedSize(true)
            val spacing = context?.getDP(8)?.toInt() ?: 0
            addItemDecoration(ShopItemDecorator(spacing, 2))
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isSnackbarInQueue) showRetrySnackbar()
    }


    override fun setLoadingBarVisibility(visible: Boolean) {
        circular_progress_bar_subscriptions_shop.visibility = if (visible) View.VISIBLE else View.GONE
    }


    override fun notifyDataChanged() = shopAdapter.notifyDataSetChanged()


    override fun showPurchaseDialog(sku: SkuDetails, billingClient: BillingClient) {
        val params = BillingFlowParams.newBuilder()
            .setSkuDetails(sku)
            .build()

        billingClient.launchBillingFlow(activity, params)
    }

    override fun showRetrySnackbar() {
        isSnackbarInQueue = true
        if (userVisibleHint)
            Snackbar.make(coordinator_layout_subscriptions_shop_root, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.snack_bar_retry)) {
                    presenter.onRetryClicked()
                    isSnackbarInQueue = false
                }
                .show()
    }
}