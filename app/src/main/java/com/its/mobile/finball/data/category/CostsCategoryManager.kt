package com.its.mobile.finball.data.category

import com.its.mobile.finball.R
import com.its.mobile.finball.ui.item.CostsCategoryItem

class CostsCategoryManager {

    private val categoryList: ArrayList<CategoryEntity> = arrayListOf(
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_FOOD,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_food
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_COMMUNAL_RENT,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_communal_rent
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_MOB_INTERNET_TV,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category__mob_internet_tv
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_CLOTHING_SHOES,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_clothing_shoes
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_TRANSPORT,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_transport
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_FUN,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_fun
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_REST,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_rest
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_STUDY,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_study
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_CHILDREN,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_children
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_PARENTS,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_parents
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_BELOVED,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_beloved
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_CARE_YOURSELF,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_care_yourself
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_CARE_HOME,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_care_home
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_HEALTH_SPORT,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_health_sport
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_GIFT,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_gift
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_PETS,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_pets
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_HOBBY,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_hobby
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_CREDIT,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_credit
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_INSURANCE,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_insurance
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_REPAIR_FURNITURE_MACHINERY,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_repair_furniture_machinery
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_INVESTMENT,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_investment,
            0,
            0f,
            true
        ),
        CategoryEntity(
            CostsCategoryItem.KEY_COSTS_CATEGORY_UNPREDICTABLE,
            R.drawable.ic_mtrl_chip_close_circle,
            R.string.costs_category_unpredictable,
            0,
            0f,
            true
        )
    )

    fun getCostsCategoryList(): ArrayList<CategoryEntity> = categoryList

    fun getCostsCategory(id: Int): CategoryEntity? {
        categoryList.filter { category -> category.id == id }.let {
            return if (it.size == 1) it[0] else null
        }
    }
}