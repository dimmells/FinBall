package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.CategoryRatingSliderModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.CategoryRatingSliderPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CategoryRatingSliderModule::class])
@ViewScope
interface CategoryRatingSliderComponent {

    fun providePresenter(): CategoryRatingSliderPresenter
}