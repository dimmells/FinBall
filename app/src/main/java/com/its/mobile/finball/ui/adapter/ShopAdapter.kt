package com.its.mobile.finball.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.its.mobile.finball.R
import com.its.mobile.finball.presentation.adapter.ShopAdapterContracts
import com.its.mobile.finball.ui.viewholder.BaseViewHolder
import com.its.mobile.finball.ui.viewholder.ShopPurchaseItemViewHolder

class ShopAdapter(
    private val adapterPresenter: ShopAdapterContracts.AdapterPresenter,
    private val purchaseItemPresenter: ShopAdapterContracts.PurchaseItemPresenter
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ShopPurchaseItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false),
            purchaseItemPresenter
        )

    override fun getItemCount(): Int = adapterPresenter.getItemsCount()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        adapterPresenter.onBindShopPurchaseItemView(holder as ShopPurchaseItemViewHolder, position)
    }
}