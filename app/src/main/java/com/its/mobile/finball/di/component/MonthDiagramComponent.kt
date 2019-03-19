package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.MonthDiagramModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.MonthDiagramPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MonthDiagramModule::class])
@ViewScope
interface MonthDiagramComponent {

    fun providePresenter(): MonthDiagramPresenter
}