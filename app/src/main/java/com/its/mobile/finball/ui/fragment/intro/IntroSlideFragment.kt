package com.its.mobile.finball.ui.fragment.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.intro.IntroSlideModule
import com.its.mobile.finball.presentation.presenter.intro.IntroSlidePresenter
import com.its.mobile.finball.presentation.view.intro.IntroSlideView
import com.its.mobile.finball.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_intro_item.*

class IntroSlideFragment : BaseFragment(), IntroSlideView {


    companion object {

        private const val PAGE_ID_KEY = "introPageId"

        fun newInstance(pageId: Int): IntroSlideFragment {
            val bundle = Bundle()
            val slideFragment = IntroSlideFragment()

            bundle.putInt(PAGE_ID_KEY, pageId)
            slideFragment.arguments = bundle

            return slideFragment
        }
    }

    @InjectPresenter
    lateinit var introSlidePresenter: IntroSlidePresenter

    @ProvidePresenter
    fun providePresenter(): IntroSlidePresenter = ApplicationLoader.applicationComponent
        .introSlideComponent(IntroSlideModule())
        .provideIntroSlidePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(PAGE_ID_KEY)?.let { introSlidePresenter.updateFragment(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_intro_item, container, false)

    override fun setLogoImage(imageId: Int) {
        image_view_intro_logo.setImageResource(imageId)
    }

    override fun setTitle(strId: Int?) {
        if (strId != null) {
            text_view_intro_title.visibility = View.VISIBLE
            text_view_intro_title.text = getString(strId)
        } else {
            text_view_intro_title.visibility = View.GONE
            text_view_intro_title.text = ""
        }
    }

    override fun setContent(strId: Int?) {
        if (strId != null) {
            text_view_intro_text.visibility = View.VISIBLE
            text_view_intro_text.text = getString(strId)
        } else {
            text_view_intro_text.visibility = View.GONE
            text_view_intro_text.text = ""
        }
    }
}