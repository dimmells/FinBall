package com.its.mobile.finball.presentation.presenter

import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.R
import com.its.mobile.finball.presentation.view.AnalyticPagerView

@InjectViewState
class AnalyticPagerPresenter: BaseMvpPresenter<AnalyticPagerView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setPagerPosition(0)
    }

    fun onNavigationItemSelected(item: MenuItem) {
        when (item.itemId) {

            R.id.item_menu_analytic_statistic_diagram -> viewState.setPagerPosition(0)

            R.id.item_menu_analytic_category_rate -> viewState.setPagerPosition(1)

            R.id.item_menu_analytic_diagram_money_box -> viewState.setPagerPosition(2)
        }
    }
}