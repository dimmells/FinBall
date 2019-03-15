package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import com.arellomobile.mvp.MvpAppCompatFragment
import com.its.mobile.finball.presentation.view.BaseMvpView
import com.its.mobile.finball.ui.navigation.BaseRouter

abstract class BaseFragment : MvpAppCompatFragment(), BaseMvpView {

    protected val router: BaseRouter
        get() = (parentFragment ?: activity) as BaseRouter

    open fun getAnalyticsWindowKey(): String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getAnalyticsWindowKey()?.also {
//            analytics().logEvent(AnalyticsEventsFactory.openWindowEvent(it))
//        }
    }

    fun showToast(text: String) {
        context?.let { makeText(it, text, Toast.LENGTH_LONG).show() }
    }

    fun showToast(stringResId: Int) {
        context?.getString(stringResId)?.let { showToast(it) }
    }

}