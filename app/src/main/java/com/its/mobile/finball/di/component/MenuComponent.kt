package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.MenuModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.MenuPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MenuModule::class])
@ViewScope
interface MenuComponent {

    fun providePresenter(): MenuPresenter
}