package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.YearDiagramPresenter
import com.its.mobile.finball.interact.YearDiagramInteract
import dagger.Module
import dagger.Provides

@Module
class YearDiagramModule {

    @Provides
    @ViewScope
    fun provideYearDiagramInteract(revenueDBManager: RevenueDBManager, costsDBManager: CostsDBManager): YearDiagramInteract = YearDiagramInteract(revenueDBManager, costsDBManager)

    @Provides
    fun provideYearDiagramPresenter(yearDiagramInteract: YearDiagramInteract): YearDiagramPresenter = YearDiagramPresenter(yearDiagramInteract)
}