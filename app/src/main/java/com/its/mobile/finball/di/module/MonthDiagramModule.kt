package com.its.mobile.finball.di.module

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.MonthDiagramInteract
import com.its.mobile.finball.presentation.presenter.MonthDiagramPresenter
import dagger.Module
import dagger.Provides

@Module
class MonthDiagramModule {

    @Provides
    @ViewScope
    fun provideMonthDiagramInteract(): MonthDiagramInteract = MonthDiagramInteract()

    @Provides
    @ViewScope
    fun provideMonthDiagramPresenter(monthDiagramInteract: MonthDiagramInteract): MonthDiagramPresenter = MonthDiagramPresenter(monthDiagramInteract)
}