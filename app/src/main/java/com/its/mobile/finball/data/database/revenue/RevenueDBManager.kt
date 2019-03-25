package com.its.mobile.finball.data.database.revenue

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class RevenueDBManager(private val revenueDao: RevenueDao) {

    private val revenueListBehaviourSubject: BehaviorSubject<Single<List<RevenueEntity>>> =
        BehaviorSubject.createDefault(revenueDao.getAll())

    val revenueListObservable: Observable<Single<List<RevenueEntity>>> =
        revenueListBehaviourSubject.map { it.subscribeOn(Schedulers.io()) }

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

    fun getInMonth(month: Calendar): Single<List<RevenueEntity>> {
        val from = Calendar.getInstance()
        from.set(Calendar.MONTH, month.time.month)
        from.set(Calendar.DAY_OF_MONTH, 1)
        from.time.apply {
            hours = 0
            minutes = 0
            seconds = 0
        }

        val to = Calendar.getInstance()
        to.set(Calendar.MONTH, month.time.month)
        to.set(Calendar.DAY_OF_MONTH, month.getActualMaximum(Calendar.DAY_OF_MONTH))
        to.time.apply {
            hours = 0
            minutes = 0
            seconds = 0
        }
        return getBetweenDates(from.time, to.time)
    }

    fun getBetweenDates(from: Date, to: Date): Single<List<RevenueEntity>> = revenueDao.getBetweenDates(from, to)
        .subscribeOn(Schedulers.io())

    fun getAmountBetweenDates(from: Date, to: Date): Single<List<Float>> = revenueDao.getAmountBetweenDates(from, to)
        .subscribeOn(Schedulers.io())

    fun getByCategory(categoryId: Int): Single<List<RevenueEntity>> = revenueDao.getByCategoryId(categoryId)
        .subscribeOn(Schedulers.io())

    fun notifyAboutUpdate() {
        revenueListBehaviourSubject.onNext(getAll())
    }

    fun insert(revenueEntity: RevenueEntity): Single<Long> = Single.fromCallable { revenueDao.insert(revenueEntity) }
        .subscribeOn(Schedulers.io())

    fun clear(): Completable = Completable.fromAction { revenueDao.clear() }
        .subscribeOn(Schedulers.io())
}