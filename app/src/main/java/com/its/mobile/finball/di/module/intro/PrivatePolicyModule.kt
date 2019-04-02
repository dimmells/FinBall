package com.its.mobile.finball.di.module.intro

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.intro.PrivatePolicyPresenter
import dagger.Module
import dagger.Provides

@Module
class PrivatePolicyModule {


    @Provides
    @ViewScope
    fun providePresenter(): PrivatePolicyPresenter = PrivatePolicyPresenter()
}