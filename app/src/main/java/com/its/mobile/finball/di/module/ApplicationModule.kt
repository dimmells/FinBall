package com.its.mobile.finball.di.module

import android.content.Context
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: ApplicationLoader) {

    @Provides
    @ApplicationScope
    fun provideContext(): Context = application

}