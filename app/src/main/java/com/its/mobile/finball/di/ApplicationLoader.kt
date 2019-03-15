package com.its.mobile.finball.di

import android.app.Application
import com.its.mobile.finball.di.component.ApplicationComponent
import com.its.mobile.finball.di.component.DaggerApplicationComponent
import com.its.mobile.finball.di.module.ApplicationModule
import com.its.mobile.finball.rx.RxErrorHandler
import io.reactivex.plugins.RxJavaPlugins

class ApplicationLoader : Application() {

    companion object {

        init {
            RxJavaPlugins.setErrorHandler(RxErrorHandler())
        }

        private lateinit var application: ApplicationLoader

        val applicationComponent
            get() = application.applicationComponent

    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        application = this
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


}