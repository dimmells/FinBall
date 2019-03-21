package com.its.mobile.finball.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.InputRevenueModule
import com.its.mobile.finball.presentation.presenter.InputRevenuePresenter
import com.its.mobile.finball.presentation.view.InputRevenueView
import kotlinx.android.synthetic.main.fragment_input_revenue_amount.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import android.view.inputmethod.InputMethodManager

class InputRevenueFragment: BaseFragment(), InputRevenueView {

    companion object {

        const val KEY_CATEGORY_ID = "categoryId"

        fun newInstance(categoryId: Int): InputRevenueFragment {
            val args = Bundle()
            val fragment = InputRevenueFragment()
            args.putInt(KEY_CATEGORY_ID, categoryId)
            fragment.arguments = args
            return fragment
        }
    }

    private var categoryId: Int = 0

    @InjectPresenter
    lateinit var inputRevenuePresenter: InputRevenuePresenter

    @ProvidePresenter
    fun providePresenter(): InputRevenuePresenter = ApplicationLoader.applicationComponent
        .inputRevenueComponent(InputRevenueModule())
        .providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args -> categoryId = args.getInt(KEY_CATEGORY_ID) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_input_revenue_amount, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputRevenuePresenter.onStart(categoryId)

        text_view_toolbar_title.text = "Revenue"
        button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }

        edit_text_input_revenue_amount.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().matches(Regex("^0"))) { edit_text_input_revenue_amount.text.delete(0,1) }
            }
        })
        edit_text_input_revenue_amount.requestFocus()
        val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.showSoftInput(edit_text_input_revenue_amount, InputMethodManager.SHOW_IMPLICIT)

        button_input_revenue_add.setOnClickListener { inputRevenuePresenter.onSaveClick(edit_text_input_revenue_amount.text.toString().toFloat()) }
    }

    override fun setCategoryName(stringResId: Int) { text_view_input_revenue_category.text = context?.getString(stringResId) }

    override fun setCategoryName(text: String) { text_view_input_revenue_category.text = text }

    override fun goBack() { fragmentManager?.popBackStack() }
}