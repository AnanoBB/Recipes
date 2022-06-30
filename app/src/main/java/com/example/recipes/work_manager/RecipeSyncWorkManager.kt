package com.example.recipes.work_manager

import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.recipes.broadcast_receiver.RecipeBroadcastReceiver
import kotlinx.coroutines.delay

class RecipeSyncWorkManager(private val context: Context, params: WorkerParameters): CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        delay(5000L)
        val intent = Intent(RecipeBroadcastReceiver.BROADCAST_ACTION)
        intent.putExtra(RecipeBroadcastReceiver.MESSAGE_TITLE, "Finished syncing with server")
        context.sendBroadcast(intent)
        return Result.success()
    }

}