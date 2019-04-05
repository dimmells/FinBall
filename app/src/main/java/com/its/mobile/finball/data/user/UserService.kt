package com.its.mobile.finball.data.user

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserService(private val userManager: UserManager) {

    fun getUserEntityObservable(): Observable<UserEntity> = userManager.userObservable

    fun getUserEntitySingle(): Single<UserEntity> = userManager.userSingle

    fun getUserEntity(): UserEntity = userManager.userEntity

    fun saveGoogleSignInToken(token: String?): Completable = getUserEntitySingle()
        .doOnSuccess { userManager.update(it.apply { googleSignInToken = token }) }
        .ignoreElement()

    fun updateUserStatus(isUserNewNow: Boolean): Completable = getUserEntitySingle()
        .doOnSuccess { userManager.update(it.apply { isNewUser = isUserNewNow }) }
        .ignoreElement()

//    fun getShopPurchases(): Single<List<ShopPurchasesResponse.Item>> = api.getShopPurchases().map { it.info }

//    fun verifyPurchase(productId: String, token: String): Single<VerifyPurchaseResponse> =
//        api.verifyPurchase(productId, token)
//            .flatMap { verification -> updateUserBalanceData().map { verification } }

}