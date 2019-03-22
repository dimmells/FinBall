package com.its.mobile.finball.interact

import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity

class RevenueSubCategoryInteract(private val subCategoryDBManager: SubCategoryDBManager) {

    fun getSubCategoryList(parentCategoryId: Int) = subCategoryDBManager.getSubCategoryList(parentCategoryId)

    fun insert(subCategoryEntity: SubCategoryEntity) = subCategoryDBManager.insert(subCategoryEntity)

    fun delete(subCategoryEntity: SubCategoryEntity) = subCategoryDBManager.delete(subCategoryEntity)
}