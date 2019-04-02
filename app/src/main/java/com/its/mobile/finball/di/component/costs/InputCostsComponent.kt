package com.its.mobile.finball.di.component.costs

import com.its.mobile.finball.di.module.costs.InputCostsModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.costs.InputCostsPresenter
import dagger.Subcomponent

@Subcomponent(modules = [InputCostsModule::class])
@ViewScope
interface InputCostsComponent {

    fun providePresenter(): InputCostsPresenter
}