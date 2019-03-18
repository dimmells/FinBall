package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.*
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Component

@Component(
        modules = [
            ApplicationModule::class,
            RevenueCategoryManagerModule::class,
            CostsCategoryManagerModule::class
        ]
)
@ApplicationScope
interface ApplicationComponent {

    fun mainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent

    fun menuComponent(menuModule: MenuModule): MenuComponent

    fun revenueCategoryComponent(revenueCategoryModule: RevenueCategoryModule): RevenueCategoryComponent

    fun costsCategoryComponent(costsCategoryModule: CostsCategoryModule): CostsCategoryComponent

    fun inputRevenueComponent(inputRevenueModule: InputRevenueModule): InputRevenueComponent

    fun inputCostsComponent(inputCostsModule: InputCostsModule): InputCostsComponent

}


