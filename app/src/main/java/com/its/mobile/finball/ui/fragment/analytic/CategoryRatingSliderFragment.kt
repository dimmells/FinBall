package com.its.mobile.finball.ui.fragment.analytic

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
import com.its.mobile.finball.di.module.analytic.CategoryRatingSliderModule
import com.its.mobile.finball.presentation.presenter.analytic.CategoryRatingSliderPresenter
import com.its.mobile.finball.presentation.view.analytic.CategoryRatingSliderView
import com.its.mobile.finball.ui.adapter.CategoryRatingViewPagerAdapter
import com.its.mobile.finball.ui.fragment.BaseFragment
import com.its.mobile.finball.ui.utils.getColorCompat
import kotlinx.android.synthetic.main.fragment_category_rating_slider.*

class CategoryRatingSliderFragment : BaseFragment(),
    CategoryRatingSliderView {

    companion object {
        fun newInstance(): CategoryRatingSliderFragment =
            CategoryRatingSliderFragment()
    }

    private lateinit var categoryRatingViewPagerAdapter: CategoryRatingViewPagerAdapter
    private var pageCount = 0

    @InjectPresenter
    lateinit var categoryRatingSliderPresenter: CategoryRatingSliderPresenter

    @ProvidePresenter
    fun providePresenter(): CategoryRatingSliderPresenter = ApplicationLoader.applicationComponent
        .categoryRatingSliderComponent(CategoryRatingSliderModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_category_rating_slider, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryRatingViewPagerAdapter = CategoryRatingViewPagerAdapter(childFragmentManager)
        pageCount = categoryRatingViewPagerAdapter.count

        view_pager_category_rating_slider_container.adapter = categoryRatingViewPagerAdapter
        view_pager_category_rating_slider_container.offscreenPageLimit = pageCount
        view_pager_category_rating_slider_container.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(position: Int) {
                categoryRatingSliderPresenter.onPageSelected()
            }
        })
    }

    override fun setActiveNavigationPoint() {
        linear_layout_category_rating_slider_navigation_point_container.removeAllViews()
        for (i in 0 until pageCount) {
            val view = ImageView(context)
            view.setImageResource(R.drawable.diagram_pager_circle_indicator)
            view.setPadding(0, 0, 20, 0)
            if (view_pager_category_rating_slider_container.currentItem == i) {
                context?.getColorCompat(R.color.colorAccent)?.let { view.setColorFilter(it) }
            }
            linear_layout_category_rating_slider_navigation_point_container.addView(view)
        }
    }
}