package com.its.mobile.finball.interact

import android.content.Context
import com.its.mobile.finball.R
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.user.UserManager
import io.reactivex.Single
import java.util.*

class MenuInteract(
    private val costsDBManager: CostsDBManager,
    private val revenueDBManager: RevenueDBManager,
    private val context: Context,
    private val userManager: UserManager
) {

    fun getMonthCosts(): Single<List<CostsEntity>> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val from = Date(calendar.timeInMillis).apply {
            hours = 0
            minutes = 0
            seconds = 0
        }
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
        val to = Date(calendar.timeInMillis).apply {
            hours = 23
            minutes = 59
            seconds = 59
        }
        return costsDBManager.getBetweenDates(from, to)
    }

    fun getMonthRevenue(): Single<List<RevenueEntity>> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val from = Date(calendar.timeInMillis).apply {
            hours = 0
            minutes = 0
            seconds = 0
        }
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
        val to = Date(calendar.timeInMillis).apply {
            hours = 23
            minutes = 59
            seconds = 59
        }
        return revenueDBManager.getBetweenDates(from, to)
    }

    fun getQuoteArray(): Array<String> = if (userManager.userEntity.isUserSubscriptionActive) context.resources.getStringArray(R.array.quote) else arrayOf("NO", "NO")

    fun getQuoteAuthorArray(): Array<String> = if (userManager.userEntity.isUserSubscriptionActive) context.resources.getStringArray(R.array.quote_author) else arrayOf("NO", "NO")

}