package com.its.mobile.finball.ui.viewholder

import android.view.View
import com.its.mobile.finball.presentation.adapter.SettingsAdapterContracts
import kotlinx.android.synthetic.main.item_settings.view.*

class SettingsViewHolder(itemView: View, private val presenter: SettingsAdapterContracts.Presenter) :
    BaseViewHolder(itemView), SettingsAdapterContracts.View {

    init {
        itemView.setOnClickListener { presenter.onItemClick(adapterPosition) }
    }

    override fun setTitle(name: String) {
        itemView.text_view_setting_item_title.text = name
    }

    override fun setIcon(iconResId: Int) {
        itemView.image_view_setting_item_icon.setImageResource(iconResId)
    }

    override fun setTopDividerVisible(visible: Boolean) {
        itemView.view_settings_item_top_div.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    override fun setBottomDividerVisible(visible: Boolean) {
        itemView.view_settings_item_bottom_div.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }
}