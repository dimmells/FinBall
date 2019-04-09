package com.its.mobile.finball.presentation.adapter

import com.its.mobile.finball.ui.viewholder.ShopPurchaseItemViewHolder

interface ShopAdapterContracts {

    interface AdapterPresenter {
        fun getItemsCount(): Int
        fun onBindShopPurchaseItemView(viewHolder: ShopPurchaseItemViewHolder, position: Int)
    }

    interface PurchaseItemPresenter {
        fun onShopPurchaseItemClicked(position: Int)
    }

    interface PurchaseItemView {
        fun setPurchase(amount: Int, iconId: Int)
        fun setPrice(price: String)
    }
}