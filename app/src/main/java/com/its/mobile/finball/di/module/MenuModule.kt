package com.its.mobile.finball.di.module

import android.content.Context
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.MenuInteract
import com.its.mobile.finball.presentation.presenter.MenuPresenter
import dagger.Module
import dagger.Provides

@Module
class MenuModule {

    @Provides
    @ViewScope
    fun provideMenuInteract(
        costsDBManager: CostsDBManager,
        revenueDBManager: RevenueDBManager,
        context: Context
    ): MenuInteract = MenuInteract(costsDBManager, revenueDBManager, context)

    @Provides
    @ViewScope
    fun provideMenuPresenter(menuInteract: MenuInteract): MenuPresenter = MenuPresenter(menuInteract)
}