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
  }

  override fun getName(): String = NAME

  override fun setBadge(count: Double) {
    // No-op on Android
  }

  override fun clearBadge() {
    // No-op on Android
  }

  override fun getBadge(promise: Promise) {
    // Always return 0 or null
    promise.resolve(0)
  }
}
