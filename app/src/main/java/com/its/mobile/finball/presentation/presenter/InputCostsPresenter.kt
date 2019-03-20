package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.interact.InputCostsInteract
import com.its.mobile.finball.presentation.view.InputCostsView
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

    fun onSaveClick(amount: Float) {
        inputCostsInteract.saveRevenue(CostsEntity(Calendar.getInstance().time, category.id, amount))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                viewState.showToast("Error: ${throwable.localizedMessage}")
            }
            .doOnSuccess {
                viewState.goBack()
//                getAll()
//                getByDay(Date(System.currentTimeMillis()))
            }
            .subscribe()
            .let { disposables.add(it) }
    }

//    fun getAll() {
//        inputCostsInteract.getAll()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onSuccess = { list ->
//                    Log.i("ROOM", "All Costs ${list.size}")
//                    list.forEach {
//                        Log.i("ROOM", "${it.date} ${it.categoryId} ${it.amount}")
//                    }
//                },
//                onError = { Log.i("ROOM", "ERROR") })
//            .let { disposables.add(it) }
//    }
//
//    fun getByDay(day: Date) {
//        inputCostsInteract.getByDay(day)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(
//                onSuccess = { list ->
//                    Log.i("ROOM", "ByDay Costs ${list.size}")
//                    list.forEach {
//                        Log.i("ROOM", "${it.date} ${it.categoryId} ${it.amount}")
//                    }
//                },
//                onError = { Log.i("ROOM", "ERROR") })
//            .let { disposables.add(it) }
//    }
}