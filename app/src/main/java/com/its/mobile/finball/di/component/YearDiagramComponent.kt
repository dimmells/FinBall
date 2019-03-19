package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.YearDiagramModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.YearDiagramPresenter
import dagger.Subcomponent

@Subcomponent(modules = [YearDiagramModule::class])
@ViewScope
interface YearDiagramComponent {

    fun providePreesnter(): YearDiagramPresenter
}