package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.AnalyticPagerModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.AnalyticPagerPresenter
import dagger.Subcomponent

@Subcomponent(modules = [AnalyticPagerModule::class])
@ViewScope
interface AnalyticPagerComponent {

    fun providePresenter(): AnalyticPagerPresenter
}