package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.InputRevenueModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.InputRevenuePresenter
import dagger.Subcomponent

@Subcomponent(modules = [InputRevenueModule::class])
@ViewScope
interface InputRevenueComponent {

    fun providePresenter(): InputRevenuePresenter
}