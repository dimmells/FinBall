package com.its.mobile.finball.di.module

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.AnalyticPagerPresenter
import dagger.Module
import dagger.Provides

@Module
class AnalyticPagerModule {

    @Provides
    @ViewScope
    fun provideAnalyticPagerPresenter(): AnalyticPagerPresenter = AnalyticPagerPresenter()
}