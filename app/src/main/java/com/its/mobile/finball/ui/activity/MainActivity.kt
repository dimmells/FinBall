package com.its.mobile.finball.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.R
import com.its.mobile.finball.data.notifications.NotificationPublisher
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.MainActivityModule
import com.its.mobile.finball.presentation.presenter.MainPresenter
import com.its.mobile.finball.presentation.view.MainView
import com.its.mobile.finball.ui.fragment.*
import com.its.mobile.finball.ui.navigation.MainRouter

class MainActivity : BaseActivity(), MainView, MainRouter {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter = ApplicationLoader.applicationComponent
        .mainActivityComponent(MainActivityModule())
        .mainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToMenu() {
        scheduleNotification()
        setFragment(MenuFragment.newInstance(), false)
    }

    override fun navigateToRevenueCategory() = setFragment(RevenueCategoryFragment.newInstance(), true, true)

    override fun navigateToCostsCategory() = setFragment(CostsCategoryFragment.newInstance(), true, true)

    override fun navigateToInputRevenue(categoryId: Int) =
        setFragment(InputRevenueFragment.newInstance(categoryId), true, true)

    override fun navigateToInputCosts(categoryId: Int) =
        setFragment(InputCostsFragment.newInstance(categoryId), true, true)

    override fun navigateToAnalytic() = setFragment(AnalyticPagerFragment.newInstance(), true, true)

    override fun navigateToSubCategoryList(parentCategoryId: Int) =
        setFragment(RevenueSubCategoryFragment.newInstance(parentCategoryId), true, true)

    override fun navigateToSetting() = setFragment(SettingFragment.newInstance(), true, true)

    private fun setFragment(fragment: Fragment, addToBackStack: Boolean, menuAnimation: Boolean = false) {
        supportFragmentManager.beginTransaction()
            .apply {
                if (menuAnimation)
                    setCustomAnimations(
                        R.anim.menu_translation_from_right,
                        R.anim.menu_translation_to_left,
                        R.anim.menu_translation_from_left,
                        R.anim.menu_translation_to_right
                    )
                else
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }
            .replace(R.id.frame_layout_main_fragment_container, fragment)
            .apply { if (addToBackStack) addToBackStack(null) }
            .commit()
    }

    override fun showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun scheduleNotification() {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)

        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 60 * 1000, pendingIntent)
    }
}
