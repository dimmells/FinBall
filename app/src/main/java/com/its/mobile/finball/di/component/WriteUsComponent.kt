package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.WriteUsModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.WriteUsPresenter
import dagger.Subcomponent

@Subcomponent(modules = [WriteUsModule::class])
@ViewScope
interface WriteUsComponent {
    fun provideWriteUsPresenter(): WriteUsPresenter
}