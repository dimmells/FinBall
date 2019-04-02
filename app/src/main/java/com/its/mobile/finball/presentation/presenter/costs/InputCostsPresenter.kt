package com.its.mobile.finball.presentation.presenter.costs

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.costs.InputCostsInteract
import com.its.mobile.finball.presentation.presenter.BaseMvpPresenter
import com.its.mobile.finball.presentation.view.costs.InputCostsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class InputCostsPresenter(private val inputCostsInteract: InputCostsInteract) : BaseMvpPresenter<InputCostsView>() {

    private lateinit var category: CategoryEntity

    fun onStart(categoryId: Int) {
        inputCostsInteract.getCategoryInfo(categoryId)?.let {
            category = it
            viewState.setCategoryName(category.titleId)
        }
    }

    fun onSaveClick(input: String) {
        if (input.isEmpty())  {
            viewState.showToast("Enter amount")
            return
        }
        val amount = input.toFloat()
        inputCostsInteract.saveRevenue(CostsEntity(Calendar.getInstance().time, category.id, amount))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                viewState.showToast("Error: ${throwable.localizedMessage}")
            }
            .doOnSuccess { viewState.goBack() }
            .subscribe()
            .let { disposables.add(it) }
    }

}