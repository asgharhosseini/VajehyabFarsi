package ir.ah.vajehyabfarsi.service
import android.app.NotificationManager
import androidx.core.app.*
import com.google.firebase.messaging.*


import dagger.hilt.android.AndroidEntryPoint
import ir.ah.vajehyabfarsi.other.util.Constance.NOTIFICATION_ID

import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService :FirebaseMessagingService() {

    @Inject
    lateinit var baseNotification: NotificationCompat.Builder


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if(remoteMessage.notification ==null){
            return
        }
        showNotification(remoteMessage)
    }

    fun showNotification(remoteMessage: RemoteMessage){
        val notificationManager:NotificationManager=getSystemService(NOTIFICATION_SERVICE)as NotificationManager
        val notification = baseNotification
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
           // .setSmallIcon(R.drawable.ic_logo)
            .build()
        notificationManager.notify(NOTIFICATION_ID,notification)

    }

}