package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.MoneyBoxInteract
import com.its.mobile.finball.presentation.presenter.MoneyBoxPresenter
import dagger.Module
import dagger.Provides

@Module
class MoneyBoxModule {

    @Provides
    @ViewScope
    fun provideMoneyBoxInteract(costsDBManager: CostsDBManager): MoneyBoxInteract = MoneyBoxInteract(costsDBManager)

    @Provides
    @ViewScope
    fun provideMoneyBoxPresenter(moneyBoxInteract: MoneyBoxInteract): MoneyBoxPresenter = MoneyBoxPresenter(moneyBoxInteract)
}