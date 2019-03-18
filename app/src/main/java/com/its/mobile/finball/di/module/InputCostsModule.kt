package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.category.CostsCategoryManager
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.InputCostsInteract
import com.its.mobile.finball.presentation.presenter.InputCostsPresenter
import dagger.Module
import dagger.Provides

@Module
class InputCostsModule {

    @Provides
    @ViewScope
    fun provideInputCostsInteract(costsCategoryManager: CostsCategoryManager, costsDBManager: CostsDBManager): InputCostsInteract = InputCostsInteract(costsCategoryManager, costsDBManager)

    @Provides
    @ViewScope
    fun provideInputCostsPrersenter(inputCostsInteract: InputCostsInteract): InputCostsPresenter = InputCostsPresenter(inputCostsInteract)
}