package com.its.mobile.finball.di.module

import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.interact.SettingInteract
import com.its.mobile.finball.presentation.presenter.SettingPresenter
import dagger.Module
import dagger.Provides

@Module
class SettingModule {

    @Provides
    @ViewScope
    fun provideSettingInteract(
        costsDBManager: CostsDBManager,
        revenueDBManager: RevenueDBManager,
        subCategoryDBManager: SubCategoryDBManager
    ): SettingInteract = SettingInteract(costsDBManager, revenueDBManager, subCategoryDBManager)

    @Provides
    @ViewScope
    fun provideSettingPresenter(settingInteract: SettingInteract): SettingPresenter = SettingPresenter(settingInteract)
}