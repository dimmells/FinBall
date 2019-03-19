package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.DiagramSliderModule
import com.its.mobile.finball.presentation.presenter.DiagramSliderPresenter
import com.its.mobile.finball.presentation.view.DiagramSliderView
import com.its.mobile.finball.ui.adapter.DiagramSliderViewPagerAdapter
import com.its.mobile.finball.ui.utils.getColorCompat
import kotlinx.android.synthetic.main.fragment_diagram_slider.*

class DiagramSliderFragment: BaseFragment(), DiagramSliderView {

    companion object {
        fun newInstance(): DiagramSliderFragment = DiagramSliderFragment()
    }

    @InjectPresenter
    lateinit var diagramSliderPresenter: DiagramSliderPresenter

    @ProvidePresenter
    fun providePresenter(): DiagramSliderPresenter = ApplicationLoader.applicationComponent
        .diagramSliderComponent(DiagramSliderModule())
        .providePresenter()

    private lateinit var diagramSliderViewPagerAdapter: DiagramSliderViewPagerAdapter
    private var pageCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_diagram_slider, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diagramSliderViewPagerAdapter = DiagramSliderViewPagerAdapter(childFragmentManager)
        pageCount = diagramSliderViewPagerAdapter.count

        view_pager_diagram_slider_container.adapter = diagramSliderViewPagerAdapter
        view_pager_diagram_slider_container.offscreenPageLimit = diagramSliderViewPagerAdapter.count
        view_pager_diagram_slider_container.setOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(position: Int) { diagramSliderPresenter.onPageSelected() }
        })
    }

    override fun setActiveNavigationPoint() {
        linear_layout_diagram_slider_navigation_point_container.removeAllViews()
        for (i in 0 until pageCount) {
            val view = ImageView(context)
            view.setImageResource(R.drawable.diagram_pager_circle_indicator)
            view.setPadding(0, 0, 20, 0)
            if (view_pager_diagram_slider_container.currentItem == i) {
                context?.getColorCompat(R.color.colorAccent)?.let { view.setColorFilter(it) }
            }
            linear_layout_diagram_slider_navigation_point_container.addView(view)
        }
    }
}