package com.its.mobile.finball.interact

import com.its.mobile.finball.data.user.UserService
import io.reactivex.Completable

class MainInteract(private val userService: UserService) {

    fun isUserAuthorized(): Boolean = userService.getUserEntity().googleSignInToken != null

    fun isNewUser(): Boolean = userService.getUserEntity().isNewUser

    fun updateUserStatus(isNewUser: Boolean) {
        userService.updateUserStatus(isNewUser).subscribe()
    }

    fun saveGoogleSignInToken(token: String?): Completable = userService.saveGoogleSignInToken(token)
}