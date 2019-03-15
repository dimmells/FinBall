package com.its.mobile.finball.ui.element

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class ViewPagerWithoutSwipe(context: Context, attr: AttributeSet?) : ViewPager(context, attr) {

    var pagingEnabled: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean =
            if (pagingEnabled) super.onTouchEvent(ev) else false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean =
            if (pagingEnabled) super.onInterceptTouchEvent(ev) else false

}