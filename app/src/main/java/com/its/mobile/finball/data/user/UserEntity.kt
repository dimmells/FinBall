package com.its.mobile.finball.data.user

data class UserEntity(
    var googleSignInToken: String? = null,
    var isNewUser: Boolean = true,
    var isUserSubscriptionActive: Boolean = false
)