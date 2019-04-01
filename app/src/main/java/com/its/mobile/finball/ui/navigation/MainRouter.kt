package com.its.mobile.finball.ui.navigation

interface MainRouter: BaseRouter {

    fun navigateToMenu()

    fun navigateToRevenueCategory()

    fun navigateToCostsCategory()

    fun navigateToInputRevenue(categoryId: Int)

    fun navigateToInputCosts(categoryId: Int)

    fun navigateToAnalytic()

    fun navigateToSubCategoryList(parentCategoryId: Int)

    fun navigateToSetting()

    fun navigateToAboutApp()

    fun navigateToWriteUs()
}