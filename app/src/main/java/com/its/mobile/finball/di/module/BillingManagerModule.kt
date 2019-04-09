package com.its.mobile.finball.di.module

import android.content.Context
import com.its.mobile.finball.data.billing.BillingManager
import com.its.mobile.finball.data.user.UserManager
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class BillingManagerModule {

    @Provides
    @ApplicationScope
    fun provideBillingModule(context: Context, userManager: UserManager): BillingManager = BillingManager(context, userManager)
}