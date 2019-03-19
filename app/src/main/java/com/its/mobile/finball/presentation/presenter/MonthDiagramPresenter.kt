package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.MonthDiagramInteract
import com.its.mobile.finball.presentation.view.MonthDiagramView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class MonthDiagramPresenter(private val monthDiagramInteract: MonthDiagramInteract): BaseMvpPresenter<MonthDiagramView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRevenueData()
        loadCostsData()
    }

    private fun loadRevenueData() {
        monthDiagramInteract.loadRevenueList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { viewState.setRevenueChartData(it) },
                onError = { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun loadCostsData() {
        monthDiagramInteract.loadCostsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { viewState.setCostsChartData(it) },
                onError = { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }
}