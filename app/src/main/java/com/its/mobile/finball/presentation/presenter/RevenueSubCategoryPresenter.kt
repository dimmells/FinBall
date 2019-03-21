package com.its.mobile.finball.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import com.its.mobile.finball.interact.RevenueSubCategoryInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.view.RevenueSubCategoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RevenueSubCategoryPresenter(private val revenueSubCategoryInteract: RevenueSubCategoryInteract) :
    BaseMvpPresenter<RevenueSubCategoryView>(), CategoryAdapterContract.AdapterPresenter,
    CategoryAdapterContract.CategoryItemPresenter {

    private val subCategoryList: MutableList<SubCategoryEntity> = ArrayList()
    private var parentCategoryId: Int = 0

    override fun getItemsCount(): Int = subCategoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = subCategoryList[position]
        view.setText(item.title)
    }

    override fun onCategoryItemClicked(position: Int) { viewState.navigateToInputRevenue(subCategoryList[position].id) }

    fun loadSubCategoryList(parentCategory: Int) {
        parentCategoryId = parentCategory
        subCategoryList.clear()
        revenueSubCategoryInteract.getSubCategoryList(parentCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    subCategoryList.addAll(list)
                    viewState.notifyDataSetChanged()
                    list.forEach {
                        Log.i("SUB", "${it.id} ${it.title}")
                    }
                },
                { error -> viewState.showToast(error.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    fun onAddClick() { viewState.showDialog() }

    fun onSaveClick(name: String) {
        val subCategoryId = parentCategoryId * 1000 + subCategoryList.size
        revenueSubCategoryInteract.insert(SubCategoryEntity(subCategoryId, parentCategoryId, name))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.closeDialog()
                    loadSubCategoryList(parentCategoryId)
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }
}