package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.InputRevenueModule
import com.its.mobile.finball.presentation.presenter.InputRevenuePresenter
import com.its.mobile.finball.presentation.view.InputRevenueView

class InputRevenueFragment: BaseFragment(), InputRevenueView {

    companion object {
        fun newInstance(): InputRevenueFragment = InputRevenueFragment()
    }

    @InjectPresenter
    lateinit var inputRevenuePresenter: InputRevenuePresenter

    @ProvidePresenter
    fun providePresenter(): InputRevenuePresenter = ApplicationLoader.applicationComponent
        .inputRevenueComponent(InputRevenueModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_input_revenue_amount, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}