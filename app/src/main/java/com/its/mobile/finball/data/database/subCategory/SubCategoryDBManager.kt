package com.its.mobile.finball.data.database.subCategory

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class SubCategoryDBManager(private val subCategoryDao: SubCategoryDao) {

    fun getAll(): Single<List<SubCategoryEntity>> = subCategoryDao.getAll()
        .subscribeOn(Schedulers.io())

    fun getSubCategory(subCategoryId: Int) = subCategoryDao.getSubCategory(subCategoryId)
        .subscribeOn(Schedulers.io())

    fun getSubCategoryList(parentCategoryId: Int): Single<List<SubCategoryEntity>> =
        subCategoryDao.getSubCategoryList(parentCategoryId)
            .subscribeOn(Schedulers.io())

    fun insert(subCategoryEntity: SubCategoryEntity): Single<Long> =
        Single.fromCallable { subCategoryDao.insert(subCategoryEntity) }
            .subscribeOn(Schedulers.io())

    fun delete(subCategoryEntity: SubCategoryEntity) =
        Completable.fromAction { subCategoryDao.delete(subCategoryEntity) }
            .subscribeOn(Schedulers.io())

    fun clear(): Completable = Completable.fromAction { subCategoryDao.clear() }
        .subscribeOn(Schedulers.io())
}
