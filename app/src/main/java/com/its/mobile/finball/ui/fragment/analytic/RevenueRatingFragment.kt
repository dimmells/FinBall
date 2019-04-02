package com.its.mobile.finball.ui.fragment.analytic

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.analytic.RevenueRatingModule
import com.its.mobile.finball.presentation.presenter.analytic.RevenueRatingPresenter
import com.its.mobile.finball.presentation.view.analytic.RevenueRatingView
import com.its.mobile.finball.ui.adapter.CategoryRecyclerAdapter
import com.its.mobile.finball.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_revenue_rating.*

class RevenueRatingFragment: BaseFragment(), RevenueRatingView {

    companion object {
        fun newInstance(): RevenueRatingFragment =
            RevenueRatingFragment()
    }

    private lateinit var revenueRatingRecyclerLayoutManager: LinearLayoutManager
    private lateinit var revenueRatingRecyclerAdapter: CategoryRecyclerAdapter

    @InjectPresenter
    lateinit var revenueRatingPresenter: RevenueRatingPresenter

    @ProvidePresenter
    fun providePresenter(): RevenueRatingPresenter = ApplicationLoader.applicationComponent
        .revenueRatingComponent(RevenueRatingModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_revenue_rating, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        revenueRatingRecyclerLayoutManager = LinearLayoutManager(context)
        revenueRatingRecyclerAdapter = CategoryRecyclerAdapter(revenueRatingPresenter, revenueRatingPresenter)

        recycler_view_revenue_rating_list.apply {
            layoutManager = revenueRatingRecyclerLayoutManager
            adapter = revenueRatingRecyclerAdapter
        }
    }

    override fun notifyDataSetChanged() { revenueRatingRecyclerAdapter.notifyDataSetChanged() }
}