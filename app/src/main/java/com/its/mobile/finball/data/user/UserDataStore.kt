package com.its.mobile.finball.data.user

import android.content.SharedPreferences

class UserDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_GOOGLE_SIGN_IN_TOKEN = "googleSignInToken"
        const val KEY_IS_NEW_USER = "isNewUser"
    }

    fun load(): UserEntity = UserEntity()
        .apply {
            googleSignInToken = sharedPreferences.getString(KEY_GOOGLE_SIGN_IN_TOKEN, null)
            isNewUser = sharedPreferences.getBoolean(KEY_IS_NEW_USER, true)
        }


    fun save(referralUser: UserEntity) = with(referralUser) {
        sharedPreferences.edit()
            .putString(KEY_GOOGLE_SIGN_IN_TOKEN, googleSignInToken)
            .putBoolean(KEY_IS_NEW_USER, isNewUser)
            .apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}