package com.its.mobile.finball.di.module

import android.content.Context
import com.its.mobile.finball.data.setting.SettingManager
import com.its.mobile.finball.data.setting.SettingRxProperties
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SettingManagerModule {

    @Provides
    @ApplicationScope
    fun provideSettingManager(settingRxProperties: SettingRxProperties, context: Context): SettingManager = SettingManager(settingRxProperties, context)
}