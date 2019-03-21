package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.category.CategoryEntity
import com.its.mobile.finball.interact.RevenueCategoryInteract
import com.its.mobile.finball.presentation.adapter.CategoryAdapterContract
import com.its.mobile.finball.presentation.view.RevenueCategoryView
import com.its.mobile.finball.ui.item.RevenueCategoryItem

@InjectViewState
class RevenueCategoryPresenter(private val revenueCategoryInteract: RevenueCategoryInteract): BaseMvpPresenter<RevenueCategoryView>(), CategoryAdapterContract.AdapterPresenter, CategoryAdapterContract.CategoryItemPresenter {

    private val categoryList: ArrayList<CategoryEntity> = ArrayList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        categoryList.addAll(revenueCategoryInteract.getCategoryList())
    }

    override fun getItemsCount(): Int = categoryList.size

    override fun onBindMenuItemView(view: CategoryAdapterContract.CategoryItemView, position: Int) {
        val item = categoryList[position]
        view.setIcon(item.iconId)
        view.setText(item.titleId)
    }

    override fun onCategoryItemClicked(position: Int) {
        val category = categoryList[position]
        if (category.isDynamic)
            viewState.navigateToSubCategory(category.id)
        else
            viewState.navigateToInputRevenueAmount(category.id)
    }
}