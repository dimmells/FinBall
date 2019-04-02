package com.its.mobile.finball.data.database.costs

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class CostsDBManager(private val costsDao: CostsDao) {

    private val costsListBehaviourSubject: BehaviorSubject<Single<List<CostsEntity>>> =
        BehaviorSubject.createDefault(costsDao.getAll())

    val costsListObservable: Observable<Single<List<CostsEntity>>> =
        costsListBehaviourSubject.map { it.subscribeOn(Schedulers.io()) }

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

    fun getInMonth(month: Calendar): Single<List<CostsEntity>> {
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

    fun getBetweenDates(from: Date, to: Date): Single<List<CostsEntity>> = costsDao.getBetweenDates(from, to)
        .subscribeOn(Schedulers.io())

    fun getByCategory(categoryId: Int): Single<List<CostsEntity>> = costsDao.getByCategory(categoryId)
        .subscribeOn(Schedulers.io())

    fun notifyAboutUpdate() {
        costsListBehaviourSubject.onNext(getAll())
    }

    fun insert(costsEntity: CostsEntity): Single<Long> = Single.fromCallable { costsDao.insert(costsEntity) }
        .subscribeOn(Schedulers.io())

    fun clear(): Completable = Completable.fromAction { costsDao.clear() }
        .subscribeOn(Schedulers.io())
}