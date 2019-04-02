package com.its.mobile.finball.di.module.settings

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.settings.WriteUsPresenter
import dagger.Module
import dagger.Provides

@Module
class WriteUsModule {

    @Provides
    @ViewScope
    fun provideWriteUsPresenter(): WriteUsPresenter =
        WriteUsPresenter()
}