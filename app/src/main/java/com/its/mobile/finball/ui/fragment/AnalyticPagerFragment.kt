package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.AnalyticPagerModule
import com.its.mobile.finball.presentation.presenter.AnalyticPagerPresenter
import com.its.mobile.finball.presentation.view.AnalyticPagerView
import com.its.mobile.finball.ui.adapter.AnalyticViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_analytic_pager.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class AnalyticPagerFragment: BaseFragment(), AnalyticPagerView {

    companion object {
        fun newInstance(): AnalyticPagerFragment = AnalyticPagerFragment()
    }

    @InjectPresenter
    lateinit var analyticPagerPresenter: AnalyticPagerPresenter

    @ProvidePresenter
    fun providePresenter(): AnalyticPagerPresenter = ApplicationLoader.applicationComponent
        .analyticPagerComponent(AnalyticPagerModule())
        .providePresenter()

    private lateinit var analyticViewPagerAdapter: AnalyticViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_analytic_pager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticViewPagerAdapter = AnalyticViewPagerAdapter(childFragmentManager)
        view_pager_analytic_pager_container.adapter = analyticViewPagerAdapter
        view_pager_analytic_pager_container.offscreenPageLimit = analyticViewPagerAdapter.count

        text_view_toolbar_title.text = "Analytic"
        button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }
        bottom_navigation_view_analytic_pager_navigation.setOnNavigationItemSelectedListener { item ->
            analyticPagerPresenter.onNavigationItemSelected(item)
            true
        }
    }

    override fun setPagerPosition(position: Int) {
        view_pager_analytic_pager_container.currentItem = position
    }
}