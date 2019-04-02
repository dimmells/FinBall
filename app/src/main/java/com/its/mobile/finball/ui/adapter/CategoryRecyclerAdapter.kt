package com.its.mobile.finball.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.its.mobile.finball.R
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.ui.viewholder.CategoryItemViewHolder

class CategoryRecyclerAdapter(
    private val adapterPresenter: CategoryAdapterContract.AdapterPresenter,
    private val itemPresenter: CategoryAdapterContract.CategoryItemPresenter
) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): CategoryItemViewHolder =
        CategoryItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false),
            itemPresenter
        )

    override fun getItemCount(): Int = adapterPresenter.getItemsCount()

    override fun onBindViewHolder(viewHolder: CategoryItemViewHolder, position: Int) {
        adapterPresenter.onBindMenuItemView(viewHolder, position)
    }
}