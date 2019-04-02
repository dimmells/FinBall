package com.its.mobile.finball.ui.fragment.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.its.mobile.finball.R
import com.its.mobile.finball.ui.adapter.IntroPagerAdapter
import com.its.mobile.finball.ui.fragment.BaseFragment
import com.its.mobile.finball.ui.navigation.PrivacyPolicyRouter
import com.its.mobile.finball.ui.utils.getColorCompat
import kotlinx.android.synthetic.main.fragment_intro_pager.*

class IntroPagerFragment : BaseFragment(), PrivacyPolicyRouter {

    companion object {
        fun newInstance(): IntroPagerFragment = IntroPagerFragment()
    }

    override fun getAnalyticsWindowKey(): String? = "intro"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_intro_pager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val introPagerAdapter = IntroPagerAdapter(childFragmentManager)

        view_pager_intro.adapter = introPagerAdapter
        view_pager_intro.offscreenPageLimit = introPagerAdapter.count

        setArrowColor(introPagerAdapter.count)

        image_button_intro_next.setOnClickListener { onNextClick(introPagerAdapter.count) }
        image_button_intro_prev.setOnClickListener { onPrevClick(introPagerAdapter.count) }
    }

    private fun onNextClick(pageCount: Int) {
        view_pager_intro.currentItem += 1
        setArrowColor(pageCount)
    }

    private fun onPrevClick(pageCount: Int) {
        view_pager_intro.currentItem -= 1
        setArrowColor(pageCount)
    }

    private fun setActiveNavigationPoint(pageCount: Int) {
        linear_layout_intro_navigation_point.removeAllViews()
        for (i in 0 until pageCount) {
            val view = ImageView(context)
            view.setImageResource(R.drawable.intro_pager_circle_indicator)
            view.setPadding(0, 0, 20, 0)
            if (view_pager_intro.currentItem == i) {
                context?.getColorCompat(R.color.colorAccent)?.let { view.setColorFilter(it) }
            }
            linear_layout_intro_navigation_point.addView(view)
        }
    }

    private fun setArrowColor(pageCount: Int) {
        when (view_pager_intro.currentItem) {

            pageCount - 1 -> context?.getColorCompat(R.color.colorGrey1)?.let {
                image_button_intro_next.setColorFilter(
                    it
                )
            }
            0 -> context?.getColorCompat(R.color.colorGrey1)?.let { image_button_intro_prev.setColorFilter(it) }

            else -> {
                context?.getColorCompat(R.color.colorAccent)?.let { image_button_intro_prev.setColorFilter(it) }
                context?.getColorCompat(R.color.colorAccent)?.let { image_button_intro_next.setColorFilter(it) }
            }
        }
        setActiveNavigationPoint(pageCount)
    }

    override fun onPrivacyPolicyConfirmed() {
        (router as PrivacyPolicyRouter).onPrivacyPolicyConfirmed()
    }
}