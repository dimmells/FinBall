package com.its.mobile.finball.ui.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.its.mobile.finball.R
import com.its.mobile.finball.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_about_app.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class AboutAppFragment: BaseFragment() {

    companion object {
        fun newInstance(): AboutAppFragment =
            AboutAppFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_about_app, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_about_app_toolbar.text_view_toolbar_title.text = "About App"
        layout_about_app_toolbar.button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }
    }
}