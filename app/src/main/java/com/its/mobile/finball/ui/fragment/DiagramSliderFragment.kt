package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.DiagramSliderModule
import com.its.mobile.finball.presentation.presenter.DiagramSliderPresenter
import com.its.mobile.finball.presentation.view.DiagramSliderView

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_diagram_slider, container, false)
}