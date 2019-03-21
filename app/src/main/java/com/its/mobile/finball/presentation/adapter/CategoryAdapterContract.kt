package com.its.mobile.finball.presentation.adapter

interface CategoryAdapterContract {

    interface AdapterPresenter {
        fun getItemsCount(): Int
        fun onBindMenuItemView(view: CategoryItemView, position: Int)
    }

    interface CategoryItemPresenter {
        fun onCategoryItemClicked(position: Int)
    }

    interface CategoryItemView {
        fun setIcon(iconId: Int)
        fun setText(textId: Int)
        fun setText(text: String)
        fun setAmount(amount: Float)
    }
}