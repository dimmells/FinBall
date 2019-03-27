package com.its.mobile.finball.data.category

import com.its.mobile.finball.R
import com.its.mobile.finball.data.category.rating.CategoryRatingDataStore
import com.its.mobile.finball.ui.item.RevenueCategoryItem

class RevenueCategoryManager(private val categoryRatingDataStore: CategoryRatingDataStore) {

    private val categoryList: ArrayList<CategoryEntity> = ArrayList()

    private fun loadCategoryList(): ArrayList<CategoryEntity> {
        categoryList.clear()
        categoryList.addAll( arrayListOf(
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_SALARY,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_salary,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_SALARY)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_SPONSOR,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_sponsor,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_SPONSOR)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_BRIBE,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_bribe,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_BRIBE)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_GIFT,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_gift,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_GIFT)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_DIVIDENDS,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_dividends,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_DIVIDENDS)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_INVESTMENT,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_investment,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_INVESTMENT),
                0f,
                true
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_BUSINESS,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_business,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_BUSINESS),
                0f,
                true
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_REALTY,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_realty,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_REALTY),
                0f,
                true
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_INSURANCE,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_insurance,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_INSURANCE)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_SECURITIES_SHARES,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_securities_shares,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_SECURITIES_SHARES)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_CREDIT_BUTS,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_credit_buts,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_CREDIT_BUTS)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_UNPREDICTABLE,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_unpredictable,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_UNPREDICTABLE)
            ),
            CategoryEntity(
                RevenueCategoryItem.KEY_REVENUE_CATEGORY_GOV_BENEFIT,
                R.drawable.ic_mtrl_chip_checked_circle,
                R.string.revenue_category_gov_benefit,
                categoryRatingDataStore.getRevenueCategoryRating(RevenueCategoryItem.KEY_REVENUE_CATEGORY_GOV_BENEFIT)
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

    fun updateCategoryRating(categoryId: Int, rating: Int) = categoryRatingDataStore.updateRevenueCategoryRating(categoryId, rating)
}