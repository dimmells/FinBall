package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.user.UserManager
import com.its.mobile.finball.data.user.UserService
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [UserDataModule::class])
class UserServiceModule {

    @Provides
    @ApplicationScope
    fun provideReferralsService(userManager: UserManager): UserService =
        UserService(userManager)
}