package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.SettingModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.SettingPresenter
import dagger.Subcomponent

@Subcomponent(modules = [SettingModule::class])
@ViewScope
interface SettingComponent {

    fun providePresenter(): SettingPresenter
}