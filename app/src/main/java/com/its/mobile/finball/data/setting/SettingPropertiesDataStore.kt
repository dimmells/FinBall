package com.its.mobile.finball.data.setting

import android.content.SharedPreferences
import com.its.mobile.finball.ui.item.SettingItem

class SettingPropertiesDataStore(private val sharedPreferences: SharedPreferences) {

    private val properties = arrayOf(
        SettingProperty(SettingItem.NOTIFICATIONS, true),
        SettingProperty(SettingItem.NOTIFICATIONS_SOUND, true),
        SettingProperty(SettingItem.RATE_APP, 1),
        SettingProperty(SettingItem.IS_NEW_USER, true)
    )

    fun load(): MutableMap<String, Any> {
        val map: MutableMap<String, Any> = hashMapOf()

        for (i in 0 until properties.size) {
            val value = properties[i].value
            when (value) {
                is Boolean -> map[properties[i].propertyId] = sharedPreferences.getBoolean(properties[i].propertyId, value)
                is Int -> map[properties[i].propertyId] = sharedPreferences.getInt(properties[i].propertyId, value)
            }
        }

        return map
    }

    fun save(map: MutableMap<String, Any>) {
        val editor = this.sharedPreferences.edit()

        for (i in 0 until properties.size) {
            val key = properties[i].propertyId
            val value = map[key]
            when (value) {
                is Boolean -> editor.putBoolean(key, value)
                is Int -> editor.putInt(key, value)
            }
        }
        editor.apply()
    }

    fun <T> save(settingProperty: SettingProperty<T>) {
        val editor = this.sharedPreferences.edit()

        val key = settingProperty.propertyId
        val value = settingProperty.value
        when (value) {
            is Boolean -> editor.putBoolean(key, value)
            is Int -> editor.putInt(key, value)
        }
        editor.apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}