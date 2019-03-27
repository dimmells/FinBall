package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.github.mikephil.charting.data.PieEntry
import com.its.mobile.finball.interact.MenuInteract
import com.its.mobile.finball.presentation.view.MenuView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MenuPresenter(private val menuInteract: MenuInteract): BaseMvpPresenter<MenuView>() {

    private var revenue = 0f
    private var costs = 0f

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getTotalRevenueForMonth()
    }

    fun onResume() {
        getTotalRevenueForMonth()
    }

    private fun getTotalRevenueForMonth() {
        menuInteract.getMonthRevenue()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { revenueList ->
                    revenue = 0f
                    revenueList.forEach { revenue += it.amount }
                    getTotalCostsFroMonth()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun getTotalCostsFroMonth() {
        menuInteract.getMonthCosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { costsList ->
                    costs = 0f
                    costsList.forEach { costs += it.amount }
                    viewState.setupChart(mutableListOf(PieEntry(revenue, "$revenue"), PieEntry(costs, "$costs")))
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    fun onRevenueClicked() = viewState.navigateToRevenue()

    fun onCostsClicked() = viewState.navigateToCosts()

    fun onAnalyticClicked() = viewState.navigateToAnalytic()

    fun onSettingClicked() = viewState.navigateToSetting()
}