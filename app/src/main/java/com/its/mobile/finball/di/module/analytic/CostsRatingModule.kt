package com.its.mobile.finball.di.module.analytic

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.analytic.CostsRatingInteract
import com.its.mobile.finball.presentation.presenter.analytic.CostsRatingPresenter
import dagger.Module
import dagger.Provides

@Module
class CostsRatingModule {

    @Provides
    @ViewScope
    fun provideCostsRatingInteract(
        costsDBManager: CostsDBManager,
        costsCategoryManager: CostsCategoryManager
    ): CostsRatingInteract =
        CostsRatingInteract(costsDBManager, costsCategoryManager)

    @Provides
    @ViewScope
    fun provideCostsRatingPresenter(costsRatingInteract: CostsRatingInteract): CostsRatingPresenter =
        CostsRatingPresenter(costsRatingInteract)
}