package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.CategoryRatingSliderModule
import com.its.mobile.finball.presentation.presenter.CategoryRatingSliderPresenter
import com.its.mobile.finball.presentation.view.CategoryRatingSliderView

class CategoryRatingSliderFragment : BaseFragment(), CategoryRatingSliderView {

    companion object {
        fun newInstance(): CategoryRatingSliderFragment = CategoryRatingSliderFragment()
    }

    @InjectPresenter
    lateinit var categoryRatingSliderPresenter: CategoryRatingSliderPresenter

    @ProvidePresenter
    fun providePresenter(): CategoryRatingSliderPresenter = ApplicationLoader.applicationComponent
        .categoryRatingSliderComponent(CategoryRatingSliderModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_category_rating_slider, container, false)
}