package com.its.mobile.finball.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.MenuModule
import com.its.mobile.finball.presentation.presenter.MenuPresenter
import com.its.mobile.finball.presentation.view.MenuView
import com.its.mobile.finball.ui.navigation.MainRouter
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : BaseFragment(), MenuView {

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    @InjectPresenter
    lateinit var menuPresenter: MenuPresenter

    @ProvidePresenter
    fun providePresenter(): MenuPresenter = ApplicationLoader.applicationComponent
        .menuComponent(MenuModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_menu_revenue.setOnClickListener { menuPresenter.onRevenueClicked() }
        button_menu_costs.setOnClickListener { menuPresenter.onCostsClicked() }
        button_menu_analytic.setOnClickListener { menuPresenter.onAnalyticClicked() }
        button_menu_setting.setOnClickListener { menuPresenter.onSettingClicked() }
    }

    override fun onResume() {
        super.onResume()
        menuPresenter.onResume()
    }

    override fun setupChart(entries: MutableList<PieEntry>) {
        pie_chart_menu_correlation.setBackgroundColor(Color.TRANSPARENT)

        moveOnScreen()

        pie_chart_menu_correlation.setUsePercentValues(true)
        pie_chart_menu_correlation.description.isEnabled = false
        pie_chart_menu_correlation.maxAngle = 180f
        pie_chart_menu_correlation.rotationAngle = 180f
        pie_chart_menu_correlation.isRotationEnabled = false
        pie_chart_menu_correlation.isDrawHoleEnabled = true
        pie_chart_menu_correlation.setHoleColor(Color.TRANSPARENT)
        pie_chart_menu_correlation.animateY(1000, Easing.EasingOption.EaseInOutCubic)
        pie_chart_menu_correlation.legend.isEnabled = false


        val set = PieDataSet(entries, "")
        set.selectionShift = 5f
        set.sliceSpace = 3f
        set.colors = mutableListOf(Color.GREEN, Color.RED)

        val data = PieData(set)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTextColor(Color.WHITE)
        pie_chart_menu_correlation.data = data
        pie_chart_menu_correlation.invalidate() // refresh
    }

    private fun moveOnScreen() {
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val height = metrics.heightPixels

        val offset = (height * 0.25).toInt()

        val params = pie_chart_menu_correlation.layoutParams as RelativeLayout.LayoutParams
        params.setMargins(0, 0, 0, -offset)
        pie_chart_menu_correlation.layoutParams = params
    }

    override fun setQuote(quote: String) {
        text_view_menu_quote.text = quote
    }

    override fun setAuthor(author: String) {
        text_view_menu_quote_author.text = author
    }

    override fun navigateToRevenue() = (router as MainRouter).navigateToRevenueCategory()

    override fun navigateToCosts() = (router as MainRouter).navigateToCostsCategory()

    override fun navigateToAnalytic() = (router as MainRouter).navigateToAnalytic()

    override fun navigateToSetting() = (router as MainRouter).navigateToSetting()
}