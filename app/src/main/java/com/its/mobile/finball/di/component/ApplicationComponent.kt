package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.component.analytic.*
import com.its.mobile.finball.di.component.costs.CostsCategoryComponent
import com.its.mobile.finball.di.component.costs.InputCostsComponent
import com.its.mobile.finball.di.component.intro.IntroSlideComponent
import com.its.mobile.finball.di.component.intro.PrivatePolicyComponent
import com.its.mobile.finball.di.component.revenue.InputRevenueComponent
import com.its.mobile.finball.di.component.revenue.RevenueCategoryComponent
import com.its.mobile.finball.di.component.revenue.RevenueSubCategoryComponent
import com.its.mobile.finball.di.component.settings.SettingComponent
import com.its.mobile.finball.di.component.settings.WriteUsComponent
import com.its.mobile.finball.di.module.*
import com.its.mobile.finball.di.module.analytic.*
import com.its.mobile.finball.di.module.costs.CostsCategoryManagerModule
import com.its.mobile.finball.di.module.costs.CostsCategoryModule
import com.its.mobile.finball.di.module.costs.InputCostsModule
import com.its.mobile.finball.di.module.intro.IntroSlideModule
import com.its.mobile.finball.di.module.intro.PrivatePolicyModule
import com.its.mobile.finball.di.module.revenue.InputRevenueModule
import com.its.mobile.finball.di.module.revenue.RevenueCategoryManagerModule
import com.its.mobile.finball.di.module.revenue.RevenueCategoryModule
import com.its.mobile.finball.di.module.revenue.RevenueSubCategoryModule
import com.its.mobile.finball.di.module.settings.SettingManagerModule
import com.its.mobile.finball.di.module.settings.SettingModule
import com.its.mobile.finball.di.module.settings.SettingPropertiesModule
import com.its.mobile.finball.di.module.settings.WriteUsModule
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RevenueCategoryManagerModule::class,
        CostsCategoryManagerModule::class,
        DatabaseModule::class,
        SettingPropertiesModule::class,
        SettingManagerModule::class,
        UserServiceModule::class,
        BillingManagerModule::class
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

    fun analyticPagerComponent(analyticPagerModule: AnalyticPagerModule): AnalyticPagerComponent

    fun diagramSliderComponent(diagramSliderModule: DiagramSliderModule): DiagramSliderComponent

    fun categoryRatingSliderComponent(categoryRatingSliderModule: CategoryRatingSliderModule): CategoryRatingSliderComponent

    fun moneyBoxComponent(moneyBoxModule: MoneyBoxModule): MoneyBoxComponent

    fun monthDiagramComponent(monthDiagramModule: MonthDiagramModule): MonthDiagramComponent

    fun yearDiagramComponent(yearDiagramModule: YearDiagramModule): YearDiagramComponent

    fun revenueRatingComponent(revenueRatingModule: RevenueRatingModule): RevenueRatingComponent

    fun costsRatingComponent(costsRatingModule: CostsRatingModule): CostsRatingComponent

    fun revenueSubCategoryComponent(revenueSubCategoryModule: RevenueSubCategoryModule): RevenueSubCategoryComponent

    fun settingComponent(settingModule: SettingModule): SettingComponent

    fun writeUsComponent(writeUsModule: WriteUsModule): WriteUsComponent

    fun privatePolicyComponent(privatePolicyModule: PrivatePolicyModule): PrivatePolicyComponent

    fun introSlideComponent(introSlideModule: IntroSlideModule): IntroSlideComponent

    fun subscriptionsShopComponent(subscriptionsShopModule: SubscriptionsShopModule): SubscriptionsShopComponent

}


