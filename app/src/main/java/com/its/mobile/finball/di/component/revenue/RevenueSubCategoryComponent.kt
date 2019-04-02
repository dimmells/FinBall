package com.its.mobile.finball.di.component.revenue

import com.its.mobile.finball.di.module.revenue.RevenueSubCategoryModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.revenue.RevenueSubCategoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RevenueSubCategoryModule::class])
@ViewScope
interface RevenueSubCategoryComponent {

    fun providePresenter(): RevenueSubCategoryPresenter
}