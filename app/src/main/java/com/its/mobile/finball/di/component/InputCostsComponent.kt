package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.InputCostsModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.InputCostsPresenter
import dagger.Subcomponent

@Subcomponent(modules = [InputCostsModule::class])
@ViewScope
interface InputCostsComponent {

    fun providePresenter(): InputCostsPresenter
}