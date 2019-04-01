package com.its.mobile.finball.data.setting

data class SettingProperty<T>(
        val propertyId: String,
        var value: T
)