package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.interact.YearDiagramInteract
import com.its.mobile.finball.presentation.view.YearDiagramView
import java.util.*
import kotlin.collections.ArrayList

@InjectViewState
class YearDiagramPresenter(private val yearDiagramInteract: YearDiagramInteract): BaseMvpPresenter<YearDiagramView>() {

    private val revenueData: ArrayList<RevenueEntity> = ArrayList()
    private val costsData: ArrayList<CostsEntity> = ArrayList()
    private val dividedData: ArrayList<RevenueEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRevenueData()
        loadCostsData()
        divideData()
    }

    private fun loadRevenueData() {
        with(revenueData) {
            add(RevenueEntity(Date(969656400000), 1, 250.90f))
            add(RevenueEntity(Date(969742800000), 1, 500f))
            add(RevenueEntity(Date(969742800000), 1, 0.90f))
            add(RevenueEntity(Date(969915600000), 1, 0f))
            add(RevenueEntity(Date(970002000000), 1, 1250.40f))
            add(RevenueEntity(Date(970088400000), 1, 25f))
            add(RevenueEntity(Date(970174800000), 1, 50.45f))
            true
        }
    }

    private fun loadCostsData() {
        with(costsData) {
            add(CostsEntity(Date(969656400000), 1, 25.90f))
            add(CostsEntity(Date(969742800000), 1, 50f))
            add(CostsEntity(Date(969742800000), 1, 10.90f))
            add(CostsEntity(Date(969915600000), 1, 20f))
            add(CostsEntity(Date(970002000000), 1, 150.40f))
            add(CostsEntity(Date(970088400000), 1, 205f))
            add(CostsEntity(Date(970174800000), 1, 540.45f))
            true
        }
    }

    private fun divideData() {
        revenueData.forEachIndexed { index, revenueEntity ->
            dividedData.add(RevenueEntity(revenueEntity.date, 1, revenueEntity.amount - costsData[index].amount))
        }
        viewState.setChartData(dividedData)
    }
}