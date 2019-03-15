package com.its.mobile.finball.di.module

import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.MenuInteract
import com.its.mobile.finball.presentation.presenter.MenuPresenter
import dagger.Module
import dagger.Provides

@Module
class MenuModule {

    @Provides
    @ViewScope
    fun provideMenuInteract(): MenuInteract = MenuInteract()

    @Provides
    @ViewScope
    fun provideMenuPresenter(menuInteract: MenuInteract): MenuPresenter = MenuPresenter(menuInteract)
}