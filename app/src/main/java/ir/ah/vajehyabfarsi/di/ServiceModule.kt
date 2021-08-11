package ir.ah.vajehyabfarsi.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import ir.ah.vajehyabfarsi.*
import ir.ah.vajehyabfarsi.other.util.Constance.NOTIFICATION_CHANNEL_ID


@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideMainActivityPendingIntent(
        @ApplicationContext context: Context
    )= PendingIntent.getActivity(
        context,
        0,
        Intent(context,
            MainActivity::class.java).also {
            it.action= "test_action"
        },
        PendingIntent.FLAG_UPDATE_CURRENT
    )


    @ServiceScoped
    @Provides
    fun provideBaseNotificationBuilder(
        @ApplicationContext context: Context,
        pendingIntent: PendingIntent
    )= NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
        .setContentIntent(pendingIntent)


}