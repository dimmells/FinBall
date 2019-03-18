package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.MoneyBoxModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.MoneyBoxPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MoneyBoxModule::class])
@ViewScope
interface MoneyBoxComponent {

    fun providePresenter(): MoneyBoxPresenter
}