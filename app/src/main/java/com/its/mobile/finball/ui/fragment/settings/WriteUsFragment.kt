package com.its.mobile.finball.ui.fragment.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.settings.WriteUsModule
import com.its.mobile.finball.presentation.presenter.settings.WriteUsPresenter
import com.its.mobile.finball.presentation.view.settings.WriteUsView
import com.its.mobile.finball.ui.fragment.BaseFragment
import com.its.mobile.finball.ui.utils.addTextChangedListener
import com.its.mobile.finball.ui.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_write_us.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class WriteUsFragment: BaseFragment(), WriteUsView {
    companion object {
        fun newInstance() = WriteUsFragment()
    }

    @InjectPresenter
    lateinit var writeUsPresenter: WriteUsPresenter

    @ProvidePresenter
    fun providePresenter(): WriteUsPresenter = ApplicationLoader.applicationComponent
            .writeUsComponent(WriteUsModule())
            .provideWriteUsPresenter()

    override fun getAnalyticsWindowKey(): String? = "write_us"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_write_us, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }
        text_view_toolbar_title.text = resources.getString(R.string.setting_write_us)
        button_write_us_send.setOnClickListener { writeUsPresenter.onSendButtonClick() }
        button_write_us_send.isEnabled = false
        edit_text_write_us_message.addTextChangedListener(onChanged = { message, _,_, _ -> button_write_us_send.isEnabled = !(message.isEmpty()) })
    }

    override fun hideKeyboard() { edit_text_write_us_message.hideKeyboard() }

    override fun sendEmail() {
        val mailto = "mailto:bob@example.org" +
                "?cc=" + "" +
                "&subject=" + Uri.encode("FinBall issue") +
                "&body=" + Uri.encode(edit_text_write_us_message.text.toString())

        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)

        try {
            startActivity(emailIntent)
        } catch (e: Exception) { showToast(e.localizedMessage) }
    }

    override fun popBackStack() { fragmentManager?.popBackStack() }
}