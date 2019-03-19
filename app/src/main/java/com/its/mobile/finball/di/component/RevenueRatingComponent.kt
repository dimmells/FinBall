package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.RevenueRatingModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.RevenueRatingPresenter
import dagger.Subcomponent

@Subcomponent(modules = [RevenueRatingModule::class])
@ViewScope
interface RevenueRatingComponent {

    fun providePresenter(): RevenueRatingPresenter
}