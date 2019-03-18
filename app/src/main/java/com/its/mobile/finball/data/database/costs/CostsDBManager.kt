package com.its.mobile.finball.data.database.costs

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class CostsDBManager(private val costsDao: CostsDao) {

    fun getAll(): Single<List<CostsEntity>> = costsDao.getAll()
        .subscribeOn(Schedulers.io())

    fun getByDay(day: Date): Single<List<CostsEntity>> {
        val from = Date(day.time).apply {
            hours = 0
            minutes = 0
            seconds = 0
        }
        val to = Date(day.time).apply {
            hours = 23
            minutes = 59
            seconds = 59
        }
        return costsDao.getBetweenDates(from, to)
            .subscribeOn(Schedulers.io())
    }

    fun insert(costsEntity: CostsEntity): Single<Long> = Single.fromCallable { costsDao.insert(costsEntity) }
        .subscribeOn(Schedulers.io())

    fun clear(): Completable = Completable.fromAction { costsDao.clear() }
        .subscribeOn(Schedulers.io())
}