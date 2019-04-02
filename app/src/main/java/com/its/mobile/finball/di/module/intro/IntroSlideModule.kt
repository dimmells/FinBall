package com.its.mobile.finball.di.module.intro

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.intro.IntroSlidePresenter
import dagger.Module
import dagger.Provides

@Module
class IntroSlideModule {

    @Provides
    @ViewScope
    fun providePresenter(): IntroSlidePresenter = IntroSlidePresenter()

}