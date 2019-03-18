package com.its.mobile.finball.data.database.revenue

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class RevenueDBManager(private val revenueDao: RevenueDao) {

    fun getAll(): Single<List<RevenueEntity>> = revenueDao.getAll()
        .subscribeOn(Schedulers.io())

    fun getByDay(day: Date): Single<List<RevenueEntity>> {
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
        return revenueDao.getBetweenDates(from, to)
            .subscribeOn(Schedulers.io())
    }

    fun insert(revenueEntity: RevenueEntity): Single<Long> = Single.fromCallable { revenueDao.insert(revenueEntity) }
        .subscribeOn(Schedulers.io())

    fun clear(): Completable = Completable.fromAction { revenueDao.clear() }
        .subscribeOn(Schedulers.io())
}