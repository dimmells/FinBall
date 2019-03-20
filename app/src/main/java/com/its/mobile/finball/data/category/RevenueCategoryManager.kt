package com.its.mobile.finball.data.category

import com.its.mobile.finball.R
import com.its.mobile.finball.ui.item.RevenueCategoryItem

class RevenueCategoryManager {

    private val categoryList: ArrayList<CategoryEntity> = arrayListOf(
//        CategoryEntity(
//            RevenueCategoryItem.KEY_REVENUE_CATEGORY_ONE,
//            R.drawable.ic_mtrl_chip_close_circle,
//            R.string.one
//        ),
//        CategoryEntity(
//            RevenueCategoryItem.KEY_REVENUE_CATEGORY_TWO,
//            R.drawable.ic_mtrl_chip_checked_circle,
//            R.string.two
//        ),
//        CategoryEntity(
//            RevenueCategoryItem.KEY_REVENUE_CATEGORY_THREE,
//            R.drawable.ic_mtrl_chip_checked_circle,
//            R.string.three
//        ),
//        CategoryEntity(
//            RevenueCategoryItem.KEY_REVENUE_CATEGORY_FOUR,
//            R.drawable.ic_mtrl_chip_close_circle,
//            R.string.four
//        ),
//        CategoryEntity(
//            RevenueCategoryItem.KEY_REVENUE_CATEGORY_FIVE,
//            R.drawable.ic_mtrl_chip_checked_circle,
//            R.string.five
//        )
    )

    fun getRevenueCategoryList(): ArrayList<CategoryEntity> = categoryList

    fun getRevenueCategory(id: Int): CategoryEntity? {
        categoryList.filter { category -> category.id == id }.let {
            return if (it.size == 1) it[0] else null
        }
    }
}