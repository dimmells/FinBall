package com.its.mobile.finball.ui.fragment

import android.graphics.Color
import android.os.Bundle
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
        setupLineChart(line_chart_month_diagram_revenue, revenueDataSet, Color.GREEN)
        setupLineChart(line_chart_month_diagram_costs, costsDataSet, Color.RED)
    }

    private fun setupLineChart(lineChart: LineChart, dataSet: LineDataSet, color: Int) {
        lineChart.apply {
            legend.isEnabled = false
            isDoubleTapToZoomEnabled = false
            description.text = ""
            setTouchEnabled(true)
            setScaleEnabled(false)
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
        setupDataSet(dataSet, color)
    }

    private fun setupDataSet(dataSet: LineDataSet, lineColor: Int) {
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

        }
    }

    override fun setRevenueChartData(records: List<RevenueEntity>) {

        revenueDataSet.clear()
        revenueLabels.clear()

        if (records.isEmpty()) {
            line_chart_month_diagram_revenue.visibility = View.INVISIBLE
            return
        } else line_chart_month_diagram_revenue.visibility = View.VISIBLE

        records.forEachIndexed { i, entity ->
            revenueDataSet.addEntry(Entry(i.toFloat(), entity.amount, entity.date))
            revenueLabels.add(dateFormat.format(entity.date))
        }

        with(line_chart_month_diagram_revenue) {
            data =
                com.github.mikephil.charting.data.LineData(kotlin.collections.listOf(revenueDataSet))
            setVisibleXRangeMaximum(5f)
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

        if (records.isEmpty()) {
            line_chart_month_diagram_costs.visibility = View.INVISIBLE
            return
        } else line_chart_month_diagram_costs.visibility = View.VISIBLE

        records.forEachIndexed { i, entity ->
            costsDataSet.addEntry(Entry(i.toFloat(), entity.amount, entity.date))
            costsLabels.add(dateFormat.format(entity.date))
        }

        with(line_chart_month_diagram_costs) {
            data =
                com.github.mikephil.charting.data.LineData(kotlin.collections.listOf(costsDataSet))
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