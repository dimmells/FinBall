package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.github.mikephil.charting.data.PieEntry
import com.its.mobile.finball.interact.MenuInteract
import com.its.mobile.finball.presentation.view.MenuView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random

@InjectViewState
class MenuPresenter(private val menuInteract: MenuInteract): BaseMvpPresenter<MenuView>() {

    private var revenue = 0f
    private var costs = 0f
    private lateinit var quoteArray: Array<String>
    private lateinit var quoteAuthorArray: Array<String>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getTotalRevenueForMonth()
        quoteArray = menuInteract.getQuoteArray()
        quoteAuthorArray = menuInteract.getQuoteAuthorArray()
    }

    fun onResume() {
        getTotalRevenueForMonth()
        if (quoteArray.size == quoteAuthorArray.size) {
            val randomIndex = Random.nextInt(quoteArray.size)
            viewState.setQuote(quoteArray[randomIndex])
            viewState.setAuthor(quoteAuthorArray[randomIndex])
        }
    }

    private fun getTotalRevenueForMonth() {
        menuInteract.getMonthRevenue()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { revenueList ->
                    revenue = 0f
                    revenueList.forEach { revenue += it.amount }
                    getTotalCostsForMonth()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun getTotalCostsForMonth() {
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