package com.its.mobile.finball.ui.fragment.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.intro.PrivatePolicyModule
import com.its.mobile.finball.presentation.presenter.intro.PrivatePolicyPresenter
import com.its.mobile.finball.presentation.view.intro.PrivatePolicyView
import com.its.mobile.finball.ui.fragment.BaseFragment
import com.its.mobile.finball.ui.navigation.PrivacyPolicyRouter
import kotlinx.android.synthetic.main.fragment_intro_private_policy.*

class PrivatePolicyFragment : BaseFragment(), PrivatePolicyView {


    companion object {
        fun newInstance(): PrivatePolicyFragment = PrivatePolicyFragment()
    }

    @InjectPresenter
    lateinit var privatePolicyPresenter: PrivatePolicyPresenter

    @ProvidePresenter
    fun providePresenter(): PrivatePolicyPresenter = ApplicationLoader.applicationComponent
        .privatePolicyComponent(PrivatePolicyModule())
        .privatePolicyPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_intro_private_policy, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        check_box_private_policy.setOnClickListener { privatePolicyPresenter.onCheckBoxClick(check_box_private_policy.isChecked) }
        button_intro_get_started.setOnClickListener { onGetStartedClick() }
    }

    private fun onGetStartedClick() {
        privatePolicyPresenter.onUserAgreePolicy()
        activity?.supportFragmentManager?.popBackStack()

    }

    override fun setGetStartedButtonEnabled(enabled: Boolean) {
        button_intro_get_started.isEnabled = enabled
    }

    override fun navigateToAuthorization() {
        (router as PrivacyPolicyRouter).onPrivacyPolicyConfirmed()
    }

}