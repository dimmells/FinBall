package com.its.mobile.finball.data

import com.its.mobile.finball.R
import com.its.mobile.finball.ui.item.CostsCategoryItem

class CostsCategoryManager {

    private val categoryList: ArrayList<CategoryEntity> = arrayListOf(
        CategoryEntity(CostsCategoryItem.KEY_COSTS_CATEGORY_ONE, R.drawable.ic_mtrl_chip_close_circle, R.string.one),
        CategoryEntity(CostsCategoryItem.KEY_COSTS_CATEGORY_TWO, R.drawable.ic_mtrl_chip_close_circle, R.string.two),
        CategoryEntity(CostsCategoryItem.KEY_COSTS_CATEGORY_THREE, R.drawable.ic_mtrl_chip_checked_circle, R.string.three),
        CategoryEntity(CostsCategoryItem.KEY_COSTS_CATEGORY_FOUR, R.drawable.ic_mtrl_chip_checked_circle, R.string.four),
        CategoryEntity(CostsCategoryItem.KEY_COSTS_CATEGORY_FIVE, R.drawable.ic_mtrl_chip_checked_circle, R.string.five)
    )

    fun getCostsCategoryList(): ArrayList<CategoryEntity> = categoryList

    fun getCostsCategory(id: Int): CategoryEntity? {
        categoryList.filter { category -> category.id == id }.let {
            return if (it.size == 1) it[0] else null
        }
    }
}