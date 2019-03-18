package com.its.mobile.finball.di.module

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.CategoryRatingSliderPresenter
import dagger.Module
import dagger.Provides

@Module
class CategoryRatingSliderModule {

    @Provides
    @ViewScope
    fun provideCategoryRatingSliderPresenter(): CategoryRatingSliderPresenter = CategoryRatingSliderPresenter()
}