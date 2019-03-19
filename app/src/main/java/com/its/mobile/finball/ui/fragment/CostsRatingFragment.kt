package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.CostsRatingModule
import com.its.mobile.finball.presentation.presenter.CostsRatingPresenter
import com.its.mobile.finball.presentation.view.CostsRatingView
import com.its.mobile.finball.ui.adapter.CategoryRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_costs_rating.*

class CostsRatingFragment: BaseFragment(), CostsRatingView {

    companion object {
        fun newInstance(): CostsRatingFragment = CostsRatingFragment()
    }

    private lateinit var costsRatingRecyclerLayoutManager: LinearLayoutManager
    private lateinit var costsRatingRecyclerAdapter: CategoryRecyclerAdapter

    @InjectPresenter
    lateinit var costsRatingPresenter: CostsRatingPresenter

    @ProvidePresenter
    fun providePresenter(): CostsRatingPresenter = ApplicationLoader.applicationComponent
        .costsRatingComponent(CostsRatingModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_costs_rating, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        costsRatingRecyclerLayoutManager = LinearLayoutManager(context)
        costsRatingRecyclerAdapter = CategoryRecyclerAdapter(costsRatingPresenter, costsRatingPresenter)

        recycler_view_costs_rating_list.apply {
            layoutManager = costsRatingRecyclerLayoutManager
            adapter = costsRatingRecyclerAdapter
        }
    }

    override fun notifyDataSetChanged() { costsRatingRecyclerAdapter.notifyDataSetChanged() }
}