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
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.YearDiagramModule
import com.its.mobile.finball.presentation.presenter.YearDiagramPresenter
import com.its.mobile.finball.presentation.view.YearDiagramView
import kotlinx.android.synthetic.main.fragment_month_diagram.*
import kotlinx.android.synthetic.main.fragment_year_diagram.*
import java.text.SimpleDateFormat
import java.util.*

class YearDiagramFragment : BaseFragment(), YearDiagramView {

    companion object {
        fun newInstance(): YearDiagramFragment = YearDiagramFragment()
    }

    private lateinit var dataSet: LineDataSet
    private val labels: ArrayList<String> = ArrayList()
    private val dateFormat = SimpleDateFormat("MM.yyyy", Locale.getDefault())

    @InjectPresenter
    lateinit var yearDiagramPresenter: YearDiagramPresenter

    @ProvidePresenter
    fun providePresenter(): YearDiagramPresenter = ApplicationLoader.applicationComponent
        .yearDiagramComponent(YearDiagramModule())
        .providePreesnter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_year_diagram, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataSet = LineDataSet(ArrayList<Entry>(), getString(R.string.year))
        setupLineChart(line_chart_year_diagram, dataSet, Color.MAGENTA)
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

    override fun setChartData(records: List<RevenueEntity>) {

        dataSet.clear()
        labels.clear()

        if (records.isEmpty()) {
            line_chart_year_diagram.visibility = View.INVISIBLE
            return
        } else line_chart_year_diagram.visibility = View.VISIBLE

        records.forEachIndexed { i, entity ->
            dataSet.addEntry(Entry(i.toFloat(), entity.amount, entity.date))
            labels.add(dateFormat.format(entity.date))
        }

        with(line_chart_year_diagram) {
            data =
                com.github.mikephil.charting.data.LineData(kotlin.collections.listOf(dataSet))
            setVisibleXRangeMaximum(5f)
            moveViewToX(data.xMax)
            xAxis.valueFormatter = IAxisValueFormatter { value, _ ->
                value.toInt()
                    .takeIf { it in 0 until labels.size }
                    ?.let { labels[value.toInt()] }
                    ?: ""
            }
        }

    }
}