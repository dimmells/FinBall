package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.ApplicationModule
import com.its.mobile.finball.di.module.MainActivityModule
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Component

@Component(
        modules = [
            ApplicationModule::class
        ]
)
@ApplicationScope
interface ApplicationComponent {

    fun mainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent

}


