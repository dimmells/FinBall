package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.CostsCategoryInteract
import com.its.mobile.finball.presentation.presenter.CostsCategoryPresenter
import dagger.Module
import dagger.Provides

@Module
class CostsCategoryModule {

    @Provides
    @ViewScope
    fun provideCostsCategoryInteract(costsCategoryManager: CostsCategoryManager, costsDBManager: CostsDBManager): CostsCategoryInteract =
        CostsCategoryInteract(costsCategoryManager, costsDBManager)

    @Provides
    @ViewScope
    fun provideCostsCategoryPresenter(costsCategoryInteract: CostsCategoryInteract): CostsCategoryPresenter =
        CostsCategoryPresenter(costsCategoryInteract)
}