package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.MoneyBoxInteract
import com.its.mobile.finball.presentation.view.MoneyBoxView
import com.its.mobile.finball.ui.item.CostsCategoryItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class MoneyBoxPresenter(private val moneyBoxInteract: MoneyBoxInteract): BaseMvpPresenter<MoneyBoxView>() {

    private var isMoneyBoxInvestmentRulesOpen: Boolean = false
    private var isLastDayInMonth: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        isLastDayInMonth = isLastDayInMonth()
        updateLayout()
    }

    private fun updateLayout() {
        viewState.setMoneyBoxInputEnabled(isLastDayInMonth)
        if (!isLastDayInMonth)
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
            moneyBoxInteract.insertMoneyBoxInvestment(CostsEntity(Calendar.getInstance().time, CostsCategoryItem.KEY_COSTS_CATEGORY_INVESTMENT, amount))
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
}