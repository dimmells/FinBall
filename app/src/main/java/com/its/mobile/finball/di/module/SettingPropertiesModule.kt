package com.its.mobile.finball.di.module

import android.content.Context
import android.content.SharedPreferences
import com.its.mobile.finball.data.setting.SettingPropertiesDataStore
import com.its.mobile.finball.data.setting.SettingRxProperties
import com.its.mobile.finball.di.scope.ApplicationScope
import com.its.mobile.finball.ui.item.SPItem
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class SettingPropertiesModule {

    @Provides
    @ApplicationScope
    @SettingPropertiesQualifier
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(SPItem.SETTING_PROPERTY, Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideSettingPropertiesDataStore(@SettingPropertiesQualifier sharedPreferences: SharedPreferences): SettingPropertiesDataStore =
        SettingPropertiesDataStore(sharedPreferences)

    @Provides
    @ApplicationScope
    fun provideRxProperties(settingPropertiesDataStore: SettingPropertiesDataStore): SettingRxProperties = SettingRxProperties(settingPropertiesDataStore)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SettingPropertiesQualifier