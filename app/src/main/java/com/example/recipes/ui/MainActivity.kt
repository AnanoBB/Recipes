package com.example.recipes.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.recipes.R
import com.example.recipes.broadcast_receiver.RecipeBroadcastReceiver

import com.example.recipes.broadcast_receiver.RecipeBroadcastReceiver.Companion.NOTIFICATION_CHANNEL_ID
import com.example.recipes.broadcast_receiver.RecipeBroadcastReceiver.Companion.NOTIFICATION_CHANNEL_NAME
import com.example.recipes.work_manager.RecipeSyncWorkManager
import java.time.Duration

class MainActivity : AppCompatActivity() {
    private val receiver = RecipeBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        val intentFilter = IntentFilter(RecipeBroadcastReceiver.BROADCAST_ACTION)
        registerReceiver(receiver, intentFilter)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<RecipeSyncWorkManager>()
            .setConstraints(constraints)
            .setInitialDelay(Duration.ofSeconds(5))
            .build()
        val manager = WorkManager.getInstance(this)
        manager.enqueue(workRequest)
    }

    private fun createNotificationChannel(){
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}