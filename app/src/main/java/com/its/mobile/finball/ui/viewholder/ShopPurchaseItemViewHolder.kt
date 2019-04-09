package com.its.mobile.finball.ui.viewholder

import android.view.View
import com.its.mobile.finball.presentation.adapter.ShopAdapterContracts
import com.its.mobile.finball.ui.utils.setTextWithIcon
import com.its.mobile.finball.ui.utils.toSpacedString
import kotlinx.android.synthetic.main.item_shop.view.*

class ShopPurchaseItemViewHolder(itemView: View, presenter: ShopAdapterContracts.PurchaseItemPresenter) :
    BaseViewHolder(itemView), ShopAdapterContracts.PurchaseItemView {

    init {
        with(itemView) {
            button_shop_item_buy.setOnClickListener { presenter.onShopPurchaseItemClicked(adapterPosition) }
        }
    }

    override fun setPurchase(amount: Int, iconId: Int) {
        val text = "${amount.toSpacedString()} $"
        itemView.text_view_shop_item_purchase.setTextWithIcon(text, '$', iconId)
    }

    override fun setPrice(price: String) {
        itemView.text_view_shop_item_purchase_price.text = price
    }


}