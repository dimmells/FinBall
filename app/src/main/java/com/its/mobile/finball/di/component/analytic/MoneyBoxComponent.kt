package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.MoneyBoxModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.MoneyBoxPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MoneyBoxModule::class])
@ViewScope
interface MoneyBoxComponent {

    fun providePresenter(): MoneyBoxPresenter
}