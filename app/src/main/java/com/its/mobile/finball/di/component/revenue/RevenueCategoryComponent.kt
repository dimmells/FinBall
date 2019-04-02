package com.its.mobile.finball.di.component.revenue

import com.its.mobile.finball.di.module.revenue.RevenueCategoryModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.revenue.RevenueCategoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RevenueCategoryModule::class])
@ViewScope
interface RevenueCategoryComponent {

    fun providePresenter(): RevenueCategoryPresenter
}