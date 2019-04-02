package com.its.mobile.finball.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.its.mobile.finball.ui.fragment.analytic.CategoryRatingSliderFragment
import com.its.mobile.finball.ui.fragment.analytic.DiagramSliderFragment
import com.its.mobile.finball.ui.fragment.analytic.MoneyBoxFragment

class AnalyticViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager) {

    private val analyticFragments = arrayListOf(
        DiagramSliderFragment.newInstance(),
        CategoryRatingSliderFragment.newInstance(),
        MoneyBoxFragment.newInstance()
    )

    override fun getItem(position: Int): Fragment = analyticFragments[position]

    override fun getCount(): Int = analyticFragments.size
}