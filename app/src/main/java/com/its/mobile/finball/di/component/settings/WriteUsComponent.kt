package com.its.mobile.finball.di.component.settings

import com.its.mobile.finball.di.module.settings.WriteUsModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.settings.WriteUsPresenter
import dagger.Subcomponent

@Subcomponent(modules = [WriteUsModule::class])
@ViewScope
interface WriteUsComponent {
    fun provideWriteUsPresenter(): WriteUsPresenter
}