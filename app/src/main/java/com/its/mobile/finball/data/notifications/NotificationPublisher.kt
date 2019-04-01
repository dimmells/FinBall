package com.its.mobile.finball.data.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import com.its.mobile.finball.R
import com.its.mobile.finball.ui.activity.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class NotificationPublisher : BroadcastReceiver() {

    private var notification: Notification? = null

    override fun onReceive(context: Context, intent: Intent) {

        var channelId = ""
        var channelName = ""
        var id = 0

        val currentTime = Calendar.getInstance()

        if (SimpleDateFormat("dd", Locale.ROOT).format(currentTime.time).toInt() == Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH) &&
            (currentTime.time.hours in 9..11 || currentTime.time.hours in 19..21)) {
            val title = "Копилка"
            val content = "Сколько Вы собрали за месяц, заполните копилку"
            channelId = "money_box_channel"
            channelName = "Money Box channel"
            id = 1
            notification = getNotification(context, title, content, channelId)
        } else if (Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH) / 3 == 0 &&
            (currentTime.time.hours in 9..11 || currentTime.time.hours in 20..21)) {
            val title = "Сохранение"
            val content = "Сделайте сохранение данных"
            channelId = "money_box_channel"
            channelName = "Money Box channel"
            id = 1
            notification = getNotification(context, title, content, channelId)
        } else if (currentTime.time.hours in 14..15 || currentTime.time.hours in 19..21) {
            val title = "Расходы, доходы"
            val content = "Не забывайте сохранять свои доходы, расходы"
            channelId = "every_day_channel"
            channelName = "Every Day channel"
            id = 2
             notification = getNotification(context, title, content, channelId)
        }
        notification?.let { notification -> sendNotification(context, notification, channelId, channelName, id) }
    }

    private fun sendNotification(context: Context, notification: Notification, channelId: String, channelName: String, id: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH// else NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(channelId, channelName, importance)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification)
    }

    private fun getNotification(context: Context, notificationTitle: String, notificationContent: String, channelId: String): Notification {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_mtrl_chip_checked_circle)
            .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setContentTitle(notificationTitle)
            .setContentText(notificationContent)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        return notificationBuilder.build()
    }
}