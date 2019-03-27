package com.its.mobile.finball.data.category

class CategoryEntity(id: Int, iconId: Int, val titleId: Int, var rating: Int = 0, var amount: Float = 0f, val isDynamic: Boolean = false): BaseCategoryEntity(id, iconId)