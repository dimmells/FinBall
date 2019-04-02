package com.its.mobile.finball.di.component.settings

import com.its.mobile.finball.di.module.settings.SettingModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.settings.SettingPresenter
import dagger.Subcomponent

@Subcomponent(modules = [SettingModule::class])
@ViewScope
interface SettingComponent {

    fun providePresenter(): SettingPresenter
}