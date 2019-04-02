package com.its.mobile.finball.ui.fragment.costs

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.costs.InputCostsModule
import com.its.mobile.finball.presentation.presenter.costs.InputCostsPresenter
import com.its.mobile.finball.presentation.view.costs.InputCostsView
import com.its.mobile.finball.ui.fragment.BaseFragment
import com.its.mobile.finball.ui.utils.AmountInputFilter
import kotlinx.android.synthetic.main.fragment_input_revenue_amount.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class InputCostsFragment : BaseFragment(), InputCostsView {

    companion object {

        const val KEY_COSTS_CATEGORY_ID = "costsCategoryId"

        fun newInstance(categoryId: Int): InputCostsFragment {
            val args = Bundle()
            val fragment = InputCostsFragment()
            args.putInt(KEY_COSTS_CATEGORY_ID, categoryId)
            fragment.arguments = args
            return fragment
        }
    }

    private var categoryId: Int = 0

    @InjectPresenter
    lateinit var inputCostsPresenter: InputCostsPresenter

    @ProvidePresenter
    fun providePresenter(): InputCostsPresenter = ApplicationLoader.applicationComponent
        .inputCostsComponent(InputCostsModule())
        .providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args -> categoryId = args.getInt(KEY_COSTS_CATEGORY_ID) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_input_revenue_amount, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputCostsPresenter.onStart(categoryId)

        text_view_toolbar_title.text = "Costs"
        button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }

        edit_text_input_revenue_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().matches(Regex("^0")) || s.toString().matches(Regex("^-"))) {
                    edit_text_input_revenue_amount.text.delete(0, 1)
                }
            }
        })
        edit_text_input_revenue_amount.filters = arrayOf<InputFilter>(AmountInputFilter(8, 2))
        edit_text_input_revenue_amount.requestFocus()
        val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.showSoftInput(edit_text_input_revenue_amount, InputMethodManager.SHOW_IMPLICIT)

        button_input_revenue_add.setOnClickListener { inputCostsPresenter.onSaveClick(edit_text_input_revenue_amount.text.toString()) }
    }

    override fun setCategoryName(stringResId: Int) {
        text_view_input_revenue_category.text = context?.getString(stringResId)
    }

    override fun goBack() {
        fragmentManager?.popBackStack()
    }
}