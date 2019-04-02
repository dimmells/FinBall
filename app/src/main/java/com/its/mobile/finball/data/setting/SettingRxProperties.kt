package com.its.mobile.finball.data.setting

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SettingRxProperties(private val settingPropertiesDataStore: SettingPropertiesDataStore) {

    private val settingPropertiesBehaviorSubject: BehaviorSubject<MutableMap<String, Any>> =
        BehaviorSubject.createDefault(settingPropertiesDataStore.load())

    val settingPropertiesObservable: Observable<MutableMap<String, Any>> =
        settingPropertiesBehaviorSubject.map { it.toMap(hashMapOf()) }

    fun updateSettingsProperties(map: MutableMap<String, Any>) {
        settingPropertiesDataStore.save(map)
        settingPropertiesBehaviorSubject.onNext(map)
    }

    fun <T> setProperty(settingProperty: SettingProperty<T>) {
        settingPropertiesDataStore.save(settingProperty)
        settingPropertiesBehaviorSubject.onNext(settingPropertiesDataStore.load())
    }
}