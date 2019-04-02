package com.its.mobile.finball.di.component.costs

import com.its.mobile.finball.di.module.costs.CostsCategoryModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.costs.CostsCategoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CostsCategoryModule::class])
@ViewScope
interface CostsCategoryComponent {

    fun providePresenter(): CostsCategoryPresenter
}