package com.its.mobile.finball.di.component.revenue

import com.its.mobile.finball.di.module.revenue.InputRevenueModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.revenue.InputRevenuePresenter
import dagger.Subcomponent

@Subcomponent(modules = [InputRevenueModule::class])
@ViewScope
interface InputRevenueComponent {

    fun providePresenter(): InputRevenuePresenter
}