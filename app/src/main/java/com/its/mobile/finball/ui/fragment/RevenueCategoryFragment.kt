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
import com.its.mobile.finball.di.module.RevenueCategoryModule
import com.its.mobile.finball.presentation.presenter.RevenueCategoryPresenter
import com.its.mobile.finball.presentation.view.RevenueCategoryView
import com.its.mobile.finball.ui.adapter.CategoryRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_revenue_category.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class RevenueCategoryFragment: BaseFragment(), RevenueCategoryView {

    companion object {
        fun newInstance(): RevenueCategoryFragment = RevenueCategoryFragment()
    }

    private lateinit var categoryRecyclerLayoutManager: LinearLayoutManager
    private lateinit var categoryRecyclerAdapter: CategoryRecyclerAdapter

    @InjectPresenter
    lateinit var revenueCategoryPresenter: RevenueCategoryPresenter

    @ProvidePresenter
    fun providePresenter(): RevenueCategoryPresenter = ApplicationLoader.applicationComponent
        .revenueCategoryComponent(RevenueCategoryModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_revenue_category, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryRecyclerLayoutManager = LinearLayoutManager(context)
        categoryRecyclerAdapter = CategoryRecyclerAdapter(revenueCategoryPresenter, revenueCategoryPresenter)
        setupCategoryRecyclerList()

        layout_revenue_category_toolbar.text_view_toolbar_title.text = "Revenue Category"
        layout_revenue_category_toolbar.button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }
    }

    private fun setupCategoryRecyclerList() {
        recycler_view_revenue_category_list.apply {
            layoutManager = categoryRecyclerLayoutManager
            adapter = categoryRecyclerAdapter
        }
    }

    override fun navigateToInputRevenueAmount(categoryId: Int) {}
}