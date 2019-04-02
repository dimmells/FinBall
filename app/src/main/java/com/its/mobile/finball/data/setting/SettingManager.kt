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
        SettingEntity(SettingItem.NOTIFICATIONS, context.getString(R.string.setting_notifications), R.drawable.icon_settings_notifications_enabled, R.drawable.icon_settings_notifications_disabled),
        SettingEntity(SettingItem.NOTIFICATIONS_SOUND, context.getString(R.string.setting_notifications_sound), R.drawable.icon_settings_notifications_sound_enabled, R.drawable.icon_settings_notifications_sound_disabled),
        SettingEntity(SettingItem.IMPORT_DATA, context.getString(R.string.setting_import_data), R.drawable.icon_settings_import),
        SettingEntity(SettingItem.EXPORT_DATA, context.getString(R.string.setting_export_data), R.drawable.icon_settings_export),
        SettingEntity(SettingItem.WRITE_US, context.getString(R.string.setting_write_us), R.drawable.icon_settings_write_us),
        SettingEntity(SettingItem.RATE_APP, context.getString(R.string.setting_rate_app), R.drawable.icon_settings_rate),
        SettingEntity(SettingItem.SHARE_APP, context.getString(R.string.setting_share_app), R.drawable.icon_settings_share),
        SettingEntity(SettingItem.ABOUT_APP, context.getString( R.string.setting_about_app), R.drawable.icon_settings_about_app)
    )

    fun getSettings(): ArrayList<SettingEntity> = settingList
}