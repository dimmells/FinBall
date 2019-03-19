package com.its.mobile.finball.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.its.mobile.finball.ui.fragment.MonthDiagramFragment
import com.its.mobile.finball.ui.fragment.YearDiagramFragment

class DiagramSliderViewPagerAdapter(fragmentManager: FragmentManager?): FragmentPagerAdapter(fragmentManager) {

    private val diagramFragmentList: ArrayList<Fragment> = arrayListOf(
        MonthDiagramFragment.newInstance(),
        YearDiagramFragment.newInstance()
    )

    override fun getItem(position: Int): Fragment = diagramFragmentList[position]

    override fun getCount(): Int = diagramFragmentList.size
}