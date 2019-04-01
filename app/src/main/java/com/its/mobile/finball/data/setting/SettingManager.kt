package com.its.mobile.finball.data.setting

import android.content.Context
import com.its.mobile.finball.R
import com.its.mobile.finball.ui.item.SettingItem
import io.reactivex.Observable

class SettingManager(private val settingRxProperties: SettingRxProperties, private val context: Context) {

    fun getProperties(): Observable<MutableMap<String, Any>> {
        return settingRxProperties.settingPropertiesObservable
    }

    fun <T> setProperty(settingProperty: SettingProperty<T>) {
        settingRxProperties.setProperty(settingProperty)
    }

    private val settingList: ArrayList<SettingEntity> = arrayListOf(
        SettingEntity(SettingItem.NOTIFICATIONS, context.getString(R.string.setting_notifications), R.drawable.settings_notifications_enabled_icon, R.drawable.settings_notifications_disabled_icon),
        SettingEntity(SettingItem.NOTIFICATIONS_SOUND, context.getString(R.string.setting_notifications_sound), R.drawable.settings_notifications_sound_enabled_icon, R.drawable.settings_notifications_sound_disabled_icon),
        SettingEntity(SettingItem.IMPORT_DATA, context.getString(R.string.setting_import_data), R.drawable.settings_import_icon),
        SettingEntity(SettingItem.EXPORT_DATA, context.getString(R.string.setting_export_data), R.drawable.settings_export_icon),
        SettingEntity(SettingItem.WRITE_US, context.getString(R.string.setting_write_us), R.drawable.settings_write_us_icon),
        SettingEntity(SettingItem.RATE_APP, context.getString(R.string.setting_rate_app), R.drawable.settings_rate_icon),
        SettingEntity(SettingItem.SHARE_APP, context.getString(R.string.setting_share_app), R.drawable.settings_share_icon),
        SettingEntity(SettingItem.ABOUT_APP, context.getString( R.string.setting_about_app), R.drawable.settings_about_app_icon)
    )

    fun getSettings(): ArrayList<SettingEntity> = settingList
}