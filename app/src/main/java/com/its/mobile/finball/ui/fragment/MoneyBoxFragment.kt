package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.MoneyBoxModule
import com.its.mobile.finball.presentation.presenter.MoneyBoxPresenter
import com.its.mobile.finball.presentation.view.MoneyBoxView

class MoneyBoxFragment: BaseFragment(), MoneyBoxView {

    companion object {
        fun newInstance(): MoneyBoxFragment = MoneyBoxFragment()
    }

    @InjectPresenter
    lateinit var moneyBoxPresenter: MoneyBoxPresenter

    @ProvidePresenter
    fun providePresenter(): MoneyBoxPresenter = ApplicationLoader.applicationComponent
        .moneyBoxComponent(MoneyBoxModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_money_box, container, false)
}