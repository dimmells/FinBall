package com.its.mobile.finball.di.module.analytic

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.analytic.MonthDiagramInteract
import com.its.mobile.finball.presentation.presenter.analytic.MonthDiagramPresenter
import dagger.Module
import dagger.Provides

@Module
class MonthDiagramModule {

    @Provides
    @ViewScope
    fun provideMonthDiagramInteract(
        revenueDBManager: RevenueDBManager,
        costsDBManager: CostsDBManager
    ): MonthDiagramInteract =
        MonthDiagramInteract(revenueDBManager, costsDBManager)

    @Provides
    @ViewScope
    fun provideMonthDiagramPresenter(monthDiagramInteract: MonthDiagramInteract): MonthDiagramPresenter =
        MonthDiagramPresenter(monthDiagramInteract)
}