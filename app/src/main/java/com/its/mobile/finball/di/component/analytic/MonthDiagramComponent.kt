package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.MonthDiagramModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.MonthDiagramPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MonthDiagramModule::class])
@ViewScope
interface MonthDiagramComponent {

    fun providePresenter(): MonthDiagramPresenter
}