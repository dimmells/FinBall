package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.CostsCategoryManager
import com.its.mobile.finball.data.RevenueCategoryManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.InputCostsInteract
import com.its.mobile.finball.interact.InputRevenueInteract
import com.its.mobile.finball.presentation.presenter.InputCostsPresenter
import com.its.mobile.finball.presentation.presenter.InputRevenuePresenter
import dagger.Module
import dagger.Provides

@Module
class InputCostsModule {

    @Provides
    @ViewScope
    fun provideInputCostsInteract(costsCategoryManager: CostsCategoryManager): InputCostsInteract = InputCostsInteract(costsCategoryManager)

    @Provides
    @ViewScope
    fun provideInputCostsPrersenter(inputCostsInteract: InputCostsInteract): InputCostsPresenter = InputCostsPresenter(inputCostsInteract)
}