package com.its.mobile.finball.di.module

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.WriteUsPresenter
import dagger.Module
import dagger.Provides

@Module
class WriteUsModule {

    @Provides
    @ViewScope
    fun provideWriteUsPresenter(): WriteUsPresenter = WriteUsPresenter()
}