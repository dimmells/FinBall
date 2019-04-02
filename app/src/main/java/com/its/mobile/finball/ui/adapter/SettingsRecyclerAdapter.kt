package com.its.mobile.finball.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.its.mobile.finball.R
import com.its.mobile.finball.presentation.adapter.SettingsAdapterContracts
import com.its.mobile.finball.ui.viewholder.SettingsViewHolder

class SettingsRecyclerAdapter(
    private val adapterPresenter: SettingsAdapterContracts.AdapterPresenter,
    private val presenter: SettingsAdapterContracts.Presenter
) : RecyclerView.Adapter<SettingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder = SettingsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_settings, parent, false
        ), presenter
    )

    override fun getItemCount(): Int = adapterPresenter.getSettingsItemsCount()

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        adapterPresenter.onBindSettingItemView(holder, position)
    }
}