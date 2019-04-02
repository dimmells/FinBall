package com.its.mobile.finball.di.module.analytic

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.analytic.CategoryRatingSliderPresenter
import dagger.Module
import dagger.Provides

@Module
class CategoryRatingSliderModule {

    @Provides
    @ViewScope
    fun provideCategoryRatingSliderPresenter(): CategoryRatingSliderPresenter =
        CategoryRatingSliderPresenter()
}