package com.its.mobile.finball.presentation.presenter.analytic

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.analytic.MonthDiagramInteract
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.analytic.MonthDiagramView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@InjectViewState
class MonthDiagramPresenter(private val monthDiagramInteract: MonthDiagramInteract) :
    BaseMvpPresenter<MonthDiagramView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRevenueData()
        loadCostsData()
    }

    private fun loadRevenueData() {
        monthDiagramInteract.loadRevenueList()
            .subscribeOn(Schedulers.io())
            .map { single ->
                single
                    .subscribeOn(Schedulers.io())
                    .map { filterRevenueData(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { viewState.setRevenueChartData(it) },
                        onError = { viewState.showToast(it.localizedMessage) }
                    )
                    .let { disposables.add(it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let { disposables.add(it) }
    }

    private fun loadCostsData() {
        monthDiagramInteract.loadCostsList()
            .subscribeOn(Schedulers.io())
            .map { single ->
                single
                    .subscribeOn(Schedulers.io())
                    .map { filterCostsData(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { viewState.setCostsChartData(it) },
                        { viewState.showToast(it.localizedMessage) }
                    )
                    .let { disposables.add(it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let { disposables.add(it) }
    }

    private fun filterRevenueData(revenueList: List<RevenueEntity>): List<RevenueEntity> {
        val newList: MutableList<RevenueEntity> = ArrayList()
        var amount = 0f
        var date = Date()
        revenueList.forEach {
            if (it.date.year == date.year &&
                it.date.month == date.month &&
                SimpleDateFormat("dd", Locale.ROOT).format(date).toInt() == SimpleDateFormat("dd", Locale.ROOT).format(
                    it.date
                ).toInt()
            ) {
                amount += it.amount
            } else {
                if (amount > 0f) {
                    newList.add(RevenueEntity(date, 0, amount))
                }
                amount = it.amount
                date = it.date
            }
        }
        if (amount > 0f) newList.add(RevenueEntity(date, 0, amount))
        return newList
    }

    private fun filterCostsData(costsList: List<CostsEntity>): List<CostsEntity> {
        val newList: MutableList<CostsEntity> = ArrayList()
        var amount = 0f
        var date = Date()
        costsList.forEach {
            if (it.date.year == date.year &&
                it.date.month == date.month &&
                SimpleDateFormat("dd", Locale.ROOT).format(date).toInt() == SimpleDateFormat("dd", Locale.ROOT).format(
                    it.date
                ).toInt()
            ) {
                amount += it.amount
            } else {
                if (amount > 0f) {
                    newList.add(CostsEntity(date, 0, amount))
                }
                amount = it.amount
                date = it.date
            }
        }
        if (amount > 0f) newList.add(CostsEntity(date, 0, amount))
        return newList
    }

}