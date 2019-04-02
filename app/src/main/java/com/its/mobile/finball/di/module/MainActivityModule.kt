package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.setting.SettingManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.MainInteract
import com.its.mobile.finball.presentation.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ViewScope
    fun provideInteract(settingManager: SettingManager) = MainInteract(settingManager)

    @Provides
    @ViewScope
    fun providePresenter(interact: MainInteract) = MainPresenter(interact)


}