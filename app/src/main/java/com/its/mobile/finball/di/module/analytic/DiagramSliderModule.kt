package com.its.mobile.finball.di.module.analytic

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.DiagramSliderPresenter
import dagger.Module
import dagger.Provides

@Module
class DiagramSliderModule {

    @Provides
    @ViewScope
    fun provideDiagramSliderPresenter(): DiagramSliderPresenter =
        DiagramSliderPresenter()
}