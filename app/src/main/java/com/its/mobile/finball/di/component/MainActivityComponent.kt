package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.MainActivityModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.MainPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
@ViewScope
interface MainActivityComponent {

    fun mainPresenter(): MainPresenter

}