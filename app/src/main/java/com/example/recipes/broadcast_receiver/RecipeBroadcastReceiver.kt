package com.example.recipes.broadcast_receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.recipes.R

class RecipeBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.let {
            val manager = NotificationManagerCompat.from(it)
            val intent: Intent?
            var pendingIntent: PendingIntent? = null
            val url = p1?.getStringExtra(ACTION_URL)
            if (url != null){
                intent =  Intent(Intent.ACTION_VIEW, Uri.parse(url))
                pendingIntent = PendingIntent.getActivity(it, 0, intent, 0)
            }
            val notification = NotificationCompat
                .Builder(it, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(p1?.getStringExtra(MESSAGE_TITLE))
                .setContentIntent(pendingIntent)
                .setStyle(NotificationCompat.BigTextStyle().bigText(p1?.getStringExtra(MESSAGE_DESCRIPTION)))
                .build()
            manager.notify(0, notification)
        }
    }

    companion object{
        const val BROADCAST_ACTION = "recipebroadcastaction"
        const val MESSAGE_TITLE = "messagetitle"
        const val ACTION_URL = "actionurl"
        const val MESSAGE_DESCRIPTION = "messagedescription"
        const val NOTIFICATION_CHANNEL_NAME = "recipechannel"
        const val NOTIFICATION_CHANNEL_ID = "recipechannelID"
    }
}