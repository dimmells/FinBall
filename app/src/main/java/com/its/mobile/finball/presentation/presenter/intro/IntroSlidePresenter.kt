package com.its.mobile.finball.presentation.presenter.intro

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.R
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.intro.IntroSlideView
import com.its.mobile.finball.ui.item.IntroSliderItem

@InjectViewState
class IntroSlidePresenter : BaseMvpPresenter<IntroSlideView>() {

    fun updateFragment(pageId: Int) {
        when (pageId) {

            IntroSliderItem.INTRO_PAGE_MISSION -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.intro_mission_title)
                viewState.setContent(R.string.intro_page_text_mission)
            }

            IntroSliderItem.INTRO_PAGE_COSTS -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.costs)
                viewState.setContent(R.string.intro_page_text_costs)

            }

            IntroSliderItem.INTRO_PAGE_REVENUE -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.revenue)
                viewState.setContent(R.string.intro_page_text_revenue)
            }

            IntroSliderItem.INTRO_PAGE_ANALYTIC -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.analytic)
                viewState.setContent(R.string.intro_page_text_analytic)
            }

            IntroSliderItem.INTRO_PAGE_MONEY_BOX -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.money_box)
                viewState.setContent(R.string.money_box_rules)
            }

            IntroSliderItem.INTRO_PAGE_FIN_INDEPENDENCY -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.money_box_fin_independency_title)
                viewState.setContent(R.string.intro_page_text_fin_independency)
            }

            IntroSliderItem.INTRO_PAGE_PRICE_PER_WORK_HOUR -> {
                viewState.setLogoImage(R.drawable.logo_test)
                viewState.setTitle(R.string.money_box_price_per_work_hour_title)
                viewState.setContent(R.string.intro_page_text_price_per_work_hour)
            }
        }
    }
}