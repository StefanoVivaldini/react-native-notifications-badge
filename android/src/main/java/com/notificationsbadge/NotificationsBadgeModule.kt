package com.notificationsbadge

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.notificationsbadge.NativeNotificationsBadgeSpec
import me.leolin.shortcutbadger.ShortcutBadger

@ReactModule(name = NotificationsBadgeModule.NAME)
class NotificationsBadgeModule(reactContext: ReactApplicationContext) :
  NativeNotificationsBadgeSpec(reactContext) {

  companion object {
    const val NAME = "NotificationsBadge"
    const val NOTIFICATION_ID = 999
    const val CHANNEL_ID = "notifications_badge_channel"
  }

  private var badgeCount = 0

  override fun getName(): String = NAME

  override fun setBadge(count: Double) {
    badgeCount = count.toInt()
    val context = reactApplicationContext
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Create notification channel (Android 8+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        CHANNEL_ID,
        "Badge Notifications",
        NotificationManager.IMPORTANCE_LOW
      ).apply {
        setShowBadge(true)
        enableLights(false)
        enableVibration(false)
        setSound(null, null)
        description = "Notification used to trigger badge count"
      }
      notificationManager.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setContentTitle("a")
      .setContentText("a")
      .setSmallIcon(android.R.drawable.ic_dialog_info)
      .setAutoCancel(true)
      .setOnlyAlertOnce(true)
      .setPriority(NotificationCompat.PRIORITY_LOW)
      .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
      .setNumber(badgeCount)

    if (badgeCount > 0) {
      notificationManager.notify(NOTIFICATION_ID, builder.build())
      ShortcutBadger.applyCount(context, badgeCount)
    } else {
      clearBadge()
    }
  }

  override fun clearBadge() {
    val context = reactApplicationContext
    badgeCount = 0
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(NOTIFICATION_ID)
    ShortcutBadger.removeCount(context)
  }

  override fun getBadge(promise: Promise) {
    promise.resolve(badgeCount)
  }
}
