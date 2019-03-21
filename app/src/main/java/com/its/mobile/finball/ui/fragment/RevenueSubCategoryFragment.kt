package com.its.mobile.finball.ui.fragment

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.RevenueSubCategoryModule
import com.its.mobile.finball.presentation.presenter.RevenueSubCategoryPresenter
import com.its.mobile.finball.presentation.view.RevenueSubCategoryView
import com.its.mobile.finball.ui.adapter.CategoryRecyclerAdapter
import com.its.mobile.finball.ui.navigation.MainRouter
import kotlinx.android.synthetic.main.fragment_dialog_sub_category_create.*
import kotlinx.android.synthetic.main.fragment_dialog_sub_category_create.view.*
import kotlinx.android.synthetic.main.fragment_revenue_sub_category.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class RevenueSubCategoryFragment : BaseFragment(), RevenueSubCategoryView {

    companion object {

        private const val KEY_REVENUE_SUB_CATEGORY_KEY = "revenueSubCategoryKey"

        fun newInstance(revenueSubCategoryKey: Int): RevenueSubCategoryFragment {
            val fragment = RevenueSubCategoryFragment()
            val args = Bundle()
            args.putInt(KEY_REVENUE_SUB_CATEGORY_KEY, revenueSubCategoryKey)
            fragment.arguments = args
            return fragment
        }
    }

    private var parentCategoryId: Int = 0

    private lateinit var subCategoryRecyclerLayoutManager: LinearLayoutManager
    private lateinit var subCategoryRecyclerAdapter: CategoryRecyclerAdapter

    @InjectPresenter
    lateinit var revenueSubCategoryPresenter: RevenueSubCategoryPresenter

    @ProvidePresenter
    fun providePresenter(): RevenueSubCategoryPresenter = ApplicationLoader.applicationComponent
        .revenueSubCategoryComponent(RevenueSubCategoryModule())
        .providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { parentCategoryId = it.getInt(KEY_REVENUE_SUB_CATEGORY_KEY) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_revenue_sub_category, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        revenueSubCategoryPresenter.loadSubCategoryList(parentCategoryId)

        subCategoryRecyclerLayoutManager = LinearLayoutManager(context)
        subCategoryRecyclerAdapter = CategoryRecyclerAdapter(revenueSubCategoryPresenter, revenueSubCategoryPresenter)
        setupCategoryRecyclerList()

        layout_revenue_sub_category_toolbar.text_view_toolbar_title.text = "Revenue Sub Category"
        layout_revenue_sub_category_toolbar.button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }

        button_revenue_sub_category_add.setOnClickListener { revenueSubCategoryPresenter.onAddClick() }
    }

    private fun setupCategoryRecyclerList() {
        recycler_view_revenue_sub_category_list.apply {
            layoutManager = subCategoryRecyclerLayoutManager
            adapter = subCategoryRecyclerAdapter
        }
    }

    private lateinit var alertDialog: AlertDialog

    override fun showDialog() {
        val context = context ?: return
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_sub_category_create, null)
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("Create sub category")
        alertDialog = mBuilder.show()
        mDialogView.button_dialog_sub_category_create_save.setOnClickListener {
            revenueSubCategoryPresenter.onSaveClick(
                alertDialog.edit_text_dialog_sub_category_create_name.text.toString()
            )
            alertDialog.dismiss()
        }
    }

    override fun closeDialog() {
        alertDialog.dismiss()
    }

    override fun notifyDataSetChanged() {
        subCategoryRecyclerAdapter.notifyDataSetChanged()
    }

    override fun navigateToInputRevenue(subCategoryId: Int) = (router as MainRouter).navigateToInputRevenue(subCategoryId)
}