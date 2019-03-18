package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.DiagramSliderModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.DiagramSliderPresenter
import dagger.Subcomponent

@Subcomponent(modules = [DiagramSliderModule::class])
@ViewScope
interface DiagramSliderComponent {

    fun providePresenter(): DiagramSliderPresenter
}