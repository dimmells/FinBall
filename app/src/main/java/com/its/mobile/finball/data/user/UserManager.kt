package com.its.mobile.finball.data.user

import com.its.mobile.finball.data.user.UserDataStore
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class UserManager(private val userDataStore: UserDataStore) {

    private val userBehaviourSubject: BehaviorSubject<UserEntity> =
        BehaviorSubject.createDefault(userDataStore.load())

    val userObservable: Observable<UserEntity> = userBehaviourSubject.map { it.copy() }

    val userSingle: Single<UserEntity> = userBehaviourSubject.firstOrError().map { it.copy() }

    val userEntity: UserEntity
        get() = userBehaviourSubject.value!!.copy()

    fun update(referralUserEntity: UserEntity) {
        userDataStore.save(referralUserEntity)
        userBehaviourSubject.onNext(referralUserEntity)
    }

    fun clear() {
        update(UserEntity())
    }
}