package com.its.mobile.finball.data.category

import com.its.mobile.finball.R
import com.its.mobile.finball.ui.item.RevenueCategoryItem

class RevenueCategoryManager() {

    private val categoryList: ArrayList<CategoryEntity> = ArrayList()

    private fun loadCategoryList(): ArrayList<CategoryEntity> {
        categoryList.clear()
        categoryList.addAll( arrayListOf(
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_SALARY,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_salary
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_SPONSOR,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_sponsor
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_BRIBE,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_bribe
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_GIFT,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_gift
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_DIVIDENDS,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_dividends
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_INVESTMENT,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_investment,
                0,
                0f,
                true
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_BUSINESS,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_business,
                0,
                0f,
                true
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_REALTY,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_realty,
                0,
                0f,
                true
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_INSURANCE,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_insurance
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_SECURITIES_SHARES,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_securities_shares
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_CREDIT_BUTS,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_credit_buts
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_UNPREDICTABLE,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_unpredictable
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_GOV_BENEFIT,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_gov_benefit
            )
        ))
        return categoryList
    }

    fun getRevenueCategoryList(): ArrayList<CategoryEntity> = loadCategoryList()

    fun getRevenueCategory(id: Int): CategoryEntity? {
        categoryList.filter { category -> category.id == id }.let {
            return if (it.size == 1) it[0] else null
        }
    }
}