package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.YearDiagramModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.YearDiagramPresenter
import dagger.Subcomponent

@Subcomponent(modules = [YearDiagramModule::class])
@ViewScope
interface YearDiagramComponent {

    fun providePreesnter(): YearDiagramPresenter
}