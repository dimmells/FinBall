package com.its.mobile.finball.presentation.adapter

interface SettingsAdapterContracts {

    interface Presenter {

        fun onItemClick(position: Int)

    }

    interface AdapterPresenter {

        fun getSettingsItemsCount(): Int

        fun onBindSettingItemView(view: SettingsAdapterContracts.View, position: Int)

    }

    interface View {

        fun setTopDividerVisible(visible : Boolean)

        fun setBottomDividerVisible(visible : Boolean)

        fun setTitle(name: String)

        fun setIcon(iconResId: Int)

    }
}