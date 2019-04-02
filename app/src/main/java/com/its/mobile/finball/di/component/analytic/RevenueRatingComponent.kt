package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.RevenueRatingModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.RevenueRatingPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RevenueRatingModule::class])
@ViewScope
interface RevenueRatingComponent {

    fun providePresenter(): RevenueRatingPresenter
}