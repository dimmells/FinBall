package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.RevenueSubCategoryModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.RevenueSubCategoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RevenueSubCategoryModule::class])
@ViewScope
interface RevenueSubCategoryComponent {

    fun providePresenter(): RevenueSubCategoryPresenter
}