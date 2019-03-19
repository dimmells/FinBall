package com.its.mobile.finball.ui.viewholder

import android.view.View
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryItemViewHolder(itemView: View, presenter: CategoryAdapterContract.CategoryItemPresenter): BaseViewHolder(itemView), CategoryAdapterContract.CategoryItemView {

    init {
        itemView.setOnClickListener { presenter.onCategoryItemClicked(adapterPosition) }
    }

    override fun setIcon(iconId: Int) { itemView.image_view_category_item_icon.setImageResource(iconId) }

    override fun setText(textId: Int) { itemView.text_view_category_item_text.setText(textId) }

    override fun setAmount(amount: Float) { itemView.text_view_category_item_amount.text = amount.toString() }
}