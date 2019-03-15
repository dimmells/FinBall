package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.github.mikephil.charting.data.PieEntry
import com.its.mobile.finball.interact.MenuInteract
import com.its.mobile.finball.presentation.view.MenuView

@InjectViewState
class MenuPresenter(private val menuInteract: MenuInteract): BaseMvpPresenter<MenuView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupChart(getTotalRevenueAndCostForMonth())
    }

    private fun getTotalRevenueAndCostForMonth(): MutableList<PieEntry> {
        val revenue = 2500f
        val costs = 1000f
        return mutableListOf(PieEntry(revenue, "$revenue"), PieEntry(costs, "$costs"))
    }
}