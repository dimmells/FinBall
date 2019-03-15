package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.RevenueCategoryModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.RevenueCategoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RevenueCategoryModule::class])
@ViewScope
interface RevenueCategoryComponent {

    fun providePresenter(): RevenueCategoryPresenter
}