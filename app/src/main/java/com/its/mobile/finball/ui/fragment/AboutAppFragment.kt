package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.its.mobile.finball.R

class AboutAppFragment: BaseFragment() {

    companion object {
        fun newInstance(): AboutAppFragment = AboutAppFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_about_app, container, false)
}