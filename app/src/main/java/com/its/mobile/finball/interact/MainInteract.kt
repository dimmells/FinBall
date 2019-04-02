package com.its.mobile.finball.interact

import com.its.mobile.finball.data.setting.SettingManager
import com.its.mobile.finball.data.setting.SettingProperty
import com.its.mobile.finball.ui.item.SettingItem

class MainInteract(private val settingManager: SettingManager) {

    fun isNewUser(): Boolean =
        settingManager.getProperties()
            .firstOrError()
            .map { it[SettingItem.IS_NEW_USER] as Boolean }
            .blockingGet()

    fun updateUserStatus() {
        settingManager.setProperty(SettingProperty(SettingItem.IS_NEW_USER, false))
    }
}