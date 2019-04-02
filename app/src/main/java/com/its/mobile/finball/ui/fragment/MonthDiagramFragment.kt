package com.its.mobile.finball.ui.fragment

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.its.mobile.finball.R
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.MonthDiagramModule
import com.its.mobile.finball.presentation.presenter.MonthDiagramPresenter
import com.its.mobile.finball.presentation.view.MonthDiagramView
import kotlinx.android.synthetic.main.fragment_month_diagram.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IFillFormatter

class MonthDiagramFragment : BaseFragment(), MonthDiagramView {

    companion object {
        fun newInstance(): MonthDiagramFragment = MonthDiagramFragment()
    }

    private lateinit var revenueDataSet: LineDataSet
    private val revenueLabels: ArrayList<String> = ArrayList()
    private lateinit var costsDataSet: LineDataSet
    private val costsLabels: ArrayList<String> = ArrayList()
    private val dateFormat = SimpleDateFormat("dd.MM", Locale.getDefault())

    @InjectPresenter
    lateinit var monthDiagramPresenter: MonthDiagramPresenter

    @ProvidePresenter
    fun providePresenter(): MonthDiagramPresenter = ApplicationLoader.applicationComponent
        .monthDiagramComponent(MonthDiagramModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_month_diagram, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        revenueDataSet = LineDataSet(ArrayList<Entry>(), getString(R.string.revenue))
        costsDataSet = LineDataSet(ArrayList<Entry>(), getString(R.string.costs))
        setupLineChart(line_chart_month_diagram_revenue, revenueDataSet, Color.GREEN, R.drawable.revenue_chart_gradient)
        setupLineChart(line_chart_month_diagram_costs, costsDataSet, Color.RED, R.drawable.costs_chart_gradient)
    }

    private fun setupLineChart(lineChart: LineChart, dataSet: LineDataSet, color: Int, gradientId: Int) {
        lineChart.apply {
            legend.isEnabled = false
            isDoubleTapToZoomEnabled = false
            description.text = ""
            setTouchEnabled(true)
            setScaleEnabled(true)
            axisRight.isEnabled = false

            with(axisLeft) {
                textColor = Color.GRAY
                gridColor = Color.GRAY
                textSize = 11f
            }

            with(xAxis) {
                textColor = Color.GRAY
                gridColor = Color.GRAY
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 11f
                granularity = 1f
            }
        }
        setupDataSet(dataSet, color, gradientId)
    }

    private fun setupDataSet(dataSet: LineDataSet, lineColor: Int, gradientId: Int) {
        val context = context ?: return
        dataSet.apply {
            valueTextColor = Color.BLACK
            valueTextSize = 10f
            color = lineColor
            setCircleColorHole(Color.WHITE)
            setCircleColor(lineColor)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            isHighlightEnabled = false
            lineWidth = 2.5f
            circleHoleRadius = 1.75f
            circleRadius = 3.5f
            setDrawFilled(true)
            fillDrawable = ContextCompat.getDrawable(context, gradientId)
        }
    }

    override fun setRevenueChartData(records: List<RevenueEntity>) {

        revenueDataSet.clear()
        revenueLabels.clear()

        if (records.isEmpty()){// || records.size < 7) {
            text_view_month_diagram_revenue_no_data.visibility = View.VISIBLE
            line_chart_month_diagram_revenue.visibility = View.INVISIBLE
            return
        }

        records.forEachIndexed { i, entity ->
            revenueDataSet.addEntry(Entry(i.toFloat(), entity.amount, entity.date))
            revenueLabels.add(dateFormat.format(entity.date))
        }

        with(line_chart_month_diagram_revenue) {
            data =
                LineData(kotlin.collections.listOf(revenueDataSet))
            setVisibleXRangeMaximum(7f)
            moveViewToX(data.xMax)
            xAxis.valueFormatter = IAxisValueFormatter { value, _ ->
                value.toInt()
                    .takeIf { it in 0 until revenueLabels.size }
                    ?.let { revenueLabels[value.toInt()] }
                    ?: ""
            }
        }
    }

    override fun setCostsChartData(records: List<CostsEntity>) {

        costsDataSet.clear()
        costsLabels.clear()

        if (records.isEmpty()){// || records.size < 7) {
            text_view_month_diagram_costs_no_data.visibility = View.VISIBLE
            line_chart_month_diagram_costs.visibility = View.INVISIBLE
            return
        }

        records.forEachIndexed { i, entity ->
            costsDataSet.addEntry(Entry(i.toFloat(), entity.amount, entity.date))
            costsLabels.add(dateFormat.format(entity.date))
        }

        with(line_chart_month_diagram_costs) {
            data = LineData(kotlin.collections.listOf(costsDataSet))
            setVisibleXRangeMaximum(5f)
            moveViewToX(data.xMax)
            xAxis.valueFormatter = IAxisValueFormatter { value, _ ->
                value.toInt()
                    .takeIf { it in 0 until costsLabels.size }
                    ?.let { costsLabels[value.toInt()] }
                    ?: ""
            }
        }

    }
}