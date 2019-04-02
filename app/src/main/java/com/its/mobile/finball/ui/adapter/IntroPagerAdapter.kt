package com.its.mobile.finball.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.its.mobile.finball.ui.fragment.BaseFragment
import com.its.mobile.finball.ui.item.IntroSliderItem
import com.its.mobile.finball.ui.fragment.intro.IntroSlideFragment
import com.its.mobile.finball.ui.fragment.intro.PrivatePolicyFragment

class IntroPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager) {

    private val introFragmentList: ArrayList<BaseFragment> = ArrayList()

    init {
        with(introFragmentList) {
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_MISSION))
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_COSTS))
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_REVENUE))
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_ANALYTIC))
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_MONEY_BOX))
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_FIN_INDEPENDENCY))
            add(IntroSlideFragment.newInstance(IntroSliderItem.INTRO_PAGE_PRICE_PER_WORK_HOUR))
            add(PrivatePolicyFragment.newInstance())
        }
    }

    override fun getItem(position: Int): Fragment = introFragmentList[position]

    override fun getCount(): Int = introFragmentList.size
}