package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.CostsRatingModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.CostsRatingPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CostsRatingModule::class])
@ViewScope
interface CostsRatingComponent {

    fun providePresenter(): CostsRatingPresenter
}