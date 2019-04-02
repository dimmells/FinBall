package com.its.mobile.finball.di.component.intro

import com.its.mobile.finball.di.module.intro.IntroSlideModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.intro.IntroSlidePresenter
import dagger.Subcomponent

@Subcomponent(modules = [IntroSlideModule::class])
@ViewScope
interface IntroSlideComponent {
    fun provideIntroSlidePresenter(): IntroSlidePresenter
}