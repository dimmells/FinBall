package com.its.mobile.finball.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MenuViewPagerAdapter(manager: FragmentManager?): FragmentPagerAdapter(manager) {

    private val menuFragments = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment = menuFragments[position]

    override fun getCount(): Int = menuFragments.size
}