package com.its.mobile.finball.interact.revenue

import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import io.reactivex.Completable
import io.reactivex.Single

class RevenueSubCategoryInteract(private val subCategoryDBManager: SubCategoryDBManager) {

    fun getSubCategoryList(parentCategoryId: Int): Single<List<SubCategoryEntity>> = subCategoryDBManager.getSubCategoryList(parentCategoryId)

    fun insert(subCategoryEntity: SubCategoryEntity): Single<Long> = subCategoryDBManager.insert(subCategoryEntity)

    fun delete(subCategoryEntity: SubCategoryEntity): Completable = subCategoryDBManager.delete(subCategoryEntity)
}