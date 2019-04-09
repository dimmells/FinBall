package com.its.mobile.finball.ui.element

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ShopItemDecorator(private val itemSpacing: Int, private val columnsCount: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view) + 1

        with(outRect) {
            bottom = itemSpacing / 2
            top = itemSpacing / 2
            when (position % columnsCount) {
                1 -> {
                    right = itemSpacing / 2
                    left = itemSpacing
                }
                0 -> {
                    right = itemSpacing
                    left = itemSpacing / 2
                }
                else -> {
                    left = itemSpacing / 2
                    right = itemSpacing / 2
                }
            }
        }
    }
}