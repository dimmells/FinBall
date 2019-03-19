package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.CostsRatingModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.CostsRatingPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CostsRatingModule::class])
@ViewScope
interface CostsRatingComponent {

    fun providePresenter(): CostsRatingPresenter
}