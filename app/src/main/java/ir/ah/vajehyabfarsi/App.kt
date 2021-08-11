package ir.ah.vajehyabfarsi

import android.app.*
import android.os.*
import dagger.hilt.android.*
import ir.ah.vajehyabfarsi.other.util.Constance.NOTIFICATION_CHANNEL_ID
import ir.ah.vajehyabfarsi.other.util.Constance.NOTIFICATION_CHANNEL_NAME

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            var notificationChannel: NotificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel)
            }

        }
    }
}