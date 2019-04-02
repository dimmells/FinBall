package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.AnalyticPagerModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.AnalyticPagerPresenter
import dagger.Subcomponent

@Subcomponent(modules = [AnalyticPagerModule::class])
@ViewScope
interface AnalyticPagerComponent {

    fun providePresenter(): AnalyticPagerPresenter
}