package com.its.mobile.finball.di.module

import android.content.Context
import android.content.SharedPreferences
import com.its.mobile.finball.data.user.UserDataStore
import com.its.mobile.finball.data.user.UserManager
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class UserDataModule {

    @Provides
    @ApplicationScope
    @ReferralUserQualifier
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("ReferralUserData", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideReferralUserDataStore(@ReferralUserQualifier sharedPreferences: SharedPreferences): UserDataStore =
        UserDataStore(sharedPreferences)

    @Provides
    @ApplicationScope
    fun provideReferralUserManager(referralUserDataStore: UserDataStore): UserManager =
        UserManager(referralUserDataStore)

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ReferralUserQualifier