package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.CostsCategoryModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.CostsCategoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CostsCategoryModule::class])
@ViewScope
interface CostsCategoryComponent {

    fun providePresenter(): CostsCategoryPresenter
}