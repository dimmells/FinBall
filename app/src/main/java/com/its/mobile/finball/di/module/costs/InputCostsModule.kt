package com.its.mobile.finball.di.module.costs

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.costs.InputCostsInteract
import com.its.mobile.finball.presentation.presenter.costs.InputCostsPresenter
import dagger.Module
import dagger.Provides

@Module
class InputCostsModule {

    @Provides
    @ViewScope
    fun provideInputCostsInteract(
        costsCategoryManager: CostsCategoryManager,
        costsDBManager: CostsDBManager
    ): InputCostsInteract =
        InputCostsInteract(costsCategoryManager, costsDBManager)

    @Provides
    @ViewScope
    fun provideInputCostsPresenter(inputCostsInteract: InputCostsInteract): InputCostsPresenter =
        InputCostsPresenter(inputCostsInteract)
}