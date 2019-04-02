package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.DiagramSliderModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.DiagramSliderPresenter
import dagger.Subcomponent

@Subcomponent(modules = [DiagramSliderModule::class])
@ViewScope
interface DiagramSliderComponent {

    fun providePresenter(): DiagramSliderPresenter
}