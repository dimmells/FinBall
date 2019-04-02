package com.its.mobile.finball.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.its.mobile.finball.ui.fragment.analytic.CostsRatingFragment
import com.its.mobile.finball.ui.fragment.analytic.RevenueRatingFragment

class CategoryRatingViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf(
        RevenueRatingFragment.newInstance(),
        CostsRatingFragment.newInstance()
    )

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size
}