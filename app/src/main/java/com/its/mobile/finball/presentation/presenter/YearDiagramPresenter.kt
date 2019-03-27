package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.YearDiagramInteract
import com.its.mobile.finball.presentation.view.YearDiagramView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@InjectViewState
class YearDiagramPresenter(private val yearDiagramInteract: YearDiagramInteract): BaseMvpPresenter<YearDiagramView>() {

    private val revenueData: HashMap<String, Float> = hashMapOf()
    private val costsData: HashMap<String, Float> = hashMapOf()
    private val dividedData: ArrayList<RevenueEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRevenueData()
    }

    private fun loadRevenueData() {
        yearDiagramInteract.loadRevenue()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { revenueList ->
                    revenueData.clear()

                    var amount = 0f
                    var date = Date()
                    revenueList.forEach {
                        if (it.date.year == date.year &&
                            it.date.month == date.month) {
                            amount += it.amount
                        } else {
                            if (amount > 0f) {
                                revenueData[SimpleDateFormat("MM.yyyy", Locale.ROOT).format(date)] = amount
                            }
                            amount = it.amount
                            val calendar = Calendar.getInstance()
                            calendar.time = it.date
                            calendar.set(Calendar.DAY_OF_MONTH, 1)
                            calendar.set(Calendar.HOUR, 12)
                            calendar.set(Calendar.MINUTE, 12)
                            calendar.set(Calendar.SECOND, 12)
                            date = calendar.time

                        }
                    }
                    if (amount > 0f) revenueData[SimpleDateFormat("MM.yyyy", Locale.ROOT).format(date)] = amount
                    println("rev" + revenueData.size)

                    loadCostsData()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun loadCostsData() {
        yearDiagramInteract.loadCosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { costsList ->
                    costsData.clear()

                    var amount = 0f
                    var date = Date()
                    costsList.forEach {
                        if (it.date.year == date.year &&
                            it.date.month == date.month) {
                            amount += it.amount
                        } else {
                            if (amount > 0f) {
                                costsData[SimpleDateFormat("MM.yyyy", Locale.ROOT).format(date)] = amount
                            }
                            amount = it.amount
                            val calendar = Calendar.getInstance()
                            calendar.time = it.date
                            calendar.set(Calendar.DAY_OF_MONTH, 1)
                            calendar.set(Calendar.HOUR, 12)
                            calendar.set(Calendar.MINUTE, 12)
                            calendar.set(Calendar.SECOND, 12)
                            date = calendar.time
                        }
                    }
                    if (amount > 0f) costsData[SimpleDateFormat("MM.yyyy", Locale.ROOT).format(date)] = amount
                    println("costs" + costsData.size)

                    divideData()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun divideData() {
        dividedData.clear()

        revenueData.forEach { revenue ->
            val costs = costsData[revenue.key]
            if (costs != null) {
                dividedData.add(RevenueEntity(SimpleDateFormat("MM.yyyy", Locale.ROOT).parse(revenue.key), 0, revenue.value - costs))
            }
        }
        divideCosts()
        revenueData.forEach { revenue ->
            dividedData.add(RevenueEntity(SimpleDateFormat("MM.yyyy", Locale.ROOT).parse(revenue.key), 0, revenue.value))
        }
        costsData.forEach { costs ->
            dividedData.add(RevenueEntity(SimpleDateFormat("MM.yyyy", Locale.ROOT).parse(costs.key), 0, -costs.value))
        }

        dividedData.sortBy { it.date }
        viewState.setChartData(dividedData)
    }

    private fun divideCosts() {

        costsData.forEach { costs ->
            val key = costs.key
            if (revenueData.remove(key) != null) {
                costsData.remove(key)
                divideCosts()
                return
            }
            val revenue = revenueData[costs.key]
            if (revenue != null) {
                dividedData.add(RevenueEntity(SimpleDateFormat("MM.yyyy", Locale.ROOT).parse(costs.key), 0, revenue - costs.value))
            }
        }
    }
}