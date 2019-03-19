package com.its.mobile.finball.di.component

import com.its.mobile.finball.di.module.*
import com.its.mobile.finball.di.scope.ApplicationScope
import dagger.Component

@Component(
        modules = [
            ApplicationModule::class,
            RevenueCategoryManagerModule::class,
            CostsCategoryManagerModule::class,
            DatabaseModule::class
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

}


