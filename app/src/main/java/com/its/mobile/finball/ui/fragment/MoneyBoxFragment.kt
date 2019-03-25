package com.its.mobile.finball.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.MoneyBoxModule
import com.its.mobile.finball.presentation.presenter.MoneyBoxPresenter
import com.its.mobile.finball.presentation.view.MoneyBoxView
import kotlinx.android.synthetic.main.fragment_money_box.*


class MoneyBoxFragment : BaseFragment(), MoneyBoxView {

    companion object {
        fun newInstance(): MoneyBoxFragment = MoneyBoxFragment()
    }

    @InjectPresenter
    lateinit var moneyBoxPresenter: MoneyBoxPresenter

    @ProvidePresenter
    fun providePresenter(): MoneyBoxPresenter = ApplicationLoader.applicationComponent
        .moneyBoxComponent(MoneyBoxModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_money_box, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_money_box_investment_save.setOnClickListener {
            moneyBoxPresenter.onSaveClick(
                edit_text_money_box_investment_amount.text.toString().toFloat()
            )
        }
        button_money_box_rules_switch.setOnClickListener { moneyBoxPresenter.onMoneyBoxSwitchClick() }
        text_view_money_box_investment_rules_title.setOnClickListener { moneyBoxPresenter.onMoneyBoxSwitchClick() }

        edit_text_money_box_independency_percent.addTextChangedListener(percentChangeListener())
    }

    private fun percentChangeListener(): TextWatcher =
        object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty())
                    moneyBoxPresenter.onFinIndependencyPercentSet(s.toString().toFloat())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

    override fun setMoneyBoxInvestmentRulesVisible(isVisible: Boolean) {
        text_view_money_box_investment_rules.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun statrtRuleViewsAnimation(isVisible: Boolean) {
        button_money_box_rules_switch.animate()
            .rotation(if (isVisible) 180f else 0f)
            .duration = 200
        text_view_money_box_investment_rules.animate()
            .translationY(if (isVisible) 0f else -text_view_money_box_investment_rules.height.toFloat())
            .alpha(if (isVisible) 1f else 0f)
            .duration = 300
    }

    override fun setMoneyBoxInputEnabled(isEnabled: Boolean) {
        edit_text_money_box_investment_amount.apply {
            this.isEnabled = isEnabled
            alpha = if (isEnabled) 1f else 0.25f
        }
        button_money_box_investment_save.apply {
            this.isEnabled = isEnabled
            alpha = if (isEnabled) 1f else 0.25f
        }
    }

    override fun showWaitAlert(text: String) {
        text_view_money_box_wait_alert.visibility = View.VISIBLE
        text_view_money_box_wait_alert.text = text
    }

    override fun clearMoneyBoxInvestmentInput() {
        edit_text_money_box_investment_amount.text.clear()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun setFinIndependencyResult(result: String) { text_view_money_box_fin_independency_result_date.text = result }

    override fun setPricePerWorkHour(price: String) { text_view_money_box_price_per_work_hour_price.text = price }
}