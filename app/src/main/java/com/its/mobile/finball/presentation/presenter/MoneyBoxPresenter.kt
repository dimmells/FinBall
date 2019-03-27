package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.MoneyBoxInteract
import com.its.mobile.finball.presentation.view.MoneyBoxView
import com.its.mobile.finball.ui.item.CostsCategoryItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class MoneyBoxPresenter(private val moneyBoxInteract: MoneyBoxInteract): BaseMvpPresenter<MoneyBoxView>() {

    private var isMoneyBoxInvestmentRulesOpen: Boolean = false
    private var isLastDayInMonth: Boolean = false
    private var finIndependencyPercent = 0f
    private var investmentForLastMonth: Float = 0f
    private var costsAmountForLastMonth: Float = 0f

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        isLastDayInMonth = isLastDayInMonth()
        loadCostsForLastMonth()
        updateLayout()
    }

    private fun updateLayout() {
        viewState.setMoneyBoxInputEnabled(isLastDayInMonth)
        if (isLastDayInMonth) {
            val unpredictableTime = Calendar.getInstance()
            unpredictableTime.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
            unpredictableTime.time.apply {
                hours = 23
                minutes = 59
                seconds = 59
            }
            checkIsUnpredictableCalculated(Date(unpredictableTime.timeInMillis))
        }
        else
            viewState.showWaitAlert("Подождите ${getDaysCountToLastDayOfMonth()} дней")
    }

    fun onMoneyBoxSwitchClick() {
        isMoneyBoxInvestmentRulesOpen = !isMoneyBoxInvestmentRulesOpen
        viewState.setMoneyBoxInvestmentRulesVisible(isMoneyBoxInvestmentRulesOpen)
        viewState.statrtRuleViewsAnimation(isMoneyBoxInvestmentRulesOpen)
    }

    private fun getDaysCountToLastDayOfMonth(): Int {
        val today = Date(System.currentTimeMillis())
        return GregorianCalendar(today.year, today.month, today.day).getActualMaximum(Calendar.DAY_OF_MONTH) - SimpleDateFormat("dd", Locale.ROOT).format(today).toInt()
    }

    private fun isLastDayInMonth(): Boolean {
        val today = Date(System.currentTimeMillis())
        val daysInMonth = GregorianCalendar(today.year, today.month, today.day).getActualMaximum(Calendar.DAY_OF_MONTH)
        return daysInMonth == SimpleDateFormat("dd", Locale.ROOT).format(today).toInt()
    }

    fun onSaveClick(amount: Float) {
        if (isLastDayInMonth) {
            moneyBoxInteract.insertCosts(CostsEntity(Calendar.getInstance().time, CostsCategoryItem.KEY_COSTS_CATEGORY_INVESTMENT, amount))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showToast("Сохранено")
                        viewState.clearMoneyBoxInvestmentInput()
                        moneyBoxInteract.notifyAboutUpdate()
                    },
                    onError = { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
        }
    }

    private fun calculateUnpredictableCosts() {
        val from = Calendar.getInstance()
        from.set(Calendar.DAY_OF_MONTH, 1)
        from.time.apply {
            hours = 0
            minutes = 0
            seconds = 0
        }
        val to = Calendar.getInstance()
        to.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
        to.time.apply {
            hours = 23
            minutes = 59
            seconds = 59
        }
        moneyBoxInteract.getRevenueBetweenDates(Date(from.timeInMillis), Date(to.timeInMillis))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { monthRevenueList ->
                    var amount = 0f
                    monthRevenueList.forEach { amount += it }
                    insertUnpredictableCosts(amount, Date(to.timeInMillis))
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun checkIsUnpredictableCalculated(date: Date) {
        moneyBoxInteract.getCostsByCategory(CostsCategoryItem.KEY_COSTS_CATEGORY_UNPREDICTABLE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { revenueList ->
                    val today = Date(System.currentTimeMillis())
                    var isUnpredictableCalculated = false
                    revenueList.forEach {
                        if (it.date.year == today.year && it.date.month == today.month) { isUnpredictableCalculated = true }
                    }
                    if (!isUnpredictableCalculated) { calculateUnpredictableCosts() }
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun insertUnpredictableCosts(monthRevenueAmount: Float, date: Date) {
        val unpredictableCosts = monthRevenueAmount / 10
        moneyBoxInteract.insertCosts(CostsEntity(date, CostsCategoryItem.KEY_COSTS_CATEGORY_UNPREDICTABLE, unpredictableCosts))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { moneyBoxInteract.notifyAboutUpdate() },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    fun onFinIndependencyPercentSet(percent: Float) {
        if (percent > 0f && percent <= 100f) {
            finIndependencyPercent = percent
            loadInvestmentForLastMonth()
        } else {
            viewState.showToast("Введите значение от 0 до 100")
        }
    }

    private fun loadInvestmentForLastMonth() {
        val previousMonth = Calendar.getInstance()
        previousMonth.add(Calendar.MONTH, -1)
        moneyBoxInteract.getCostsByCategory(CostsCategoryItem.KEY_COSTS_CATEGORY_INVESTMENT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { investmentList ->
                    investmentForLastMonth = 0f
                    investmentList.filter { it.date.year == previousMonth.time.year && it.date.month == previousMonth.time.month }
                        .forEach { investmentForLastMonth += it.amount }
                    loadCostsForLastMonth()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun loadCostsForLastMonth() {
        val previousMonth = Calendar.getInstance()
        previousMonth.add(Calendar.MONTH, -1)
        moneyBoxInteract.getCostsInMonth(previousMonth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { costsList ->
                    costsAmountForLastMonth = 0f
                    costsList.forEach {
                        if (it.categoryId != CostsCategoryItem.KEY_COSTS_CATEGORY_INVESTMENT)
                            costsAmountForLastMonth += it.amount
                    }
                    calculateFinIndependency()
                    calculatePricePerWorkHour()
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun calculateFinIndependency() {
        Observable.fromCallable {
            var investmentRevenue = investmentForLastMonth
            var monthToFinIndependency = 0
            var percentRevenue = 0f
            while (percentRevenue < costsAmountForLastMonth) {
                percentRevenue = investmentRevenue * (finIndependencyPercent / 100)
                investmentRevenue += percentRevenue + investmentForLastMonth
                monthToFinIndependency++
            }
            monthToFinIndependency
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { countOfMonth ->
                    val resultDate = Calendar.getInstance()
                    resultDate.add(Calendar.MONTH, countOfMonth)
                    viewState.setFinIndependencyResult(SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(resultDate.time))
                },
                { viewState.showToast(it.localizedMessage) })
            .let { disposables.add(it) }

    }

    private fun calculatePricePerWorkHour() =
        viewState.setPricePerWorkHour(String.format("%.2f", costsAmountForLastMonth / 160) + "/час")
}