package com.its.mobile.finball.di.component.analytic

import com.its.mobile.finball.di.module.analytic.CategoryRatingSliderModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.CategoryRatingSliderPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CategoryRatingSliderModule::class])
@ViewScope
interface CategoryRatingSliderComponent {

    fun providePresenter(): CategoryRatingSliderPresenter
}