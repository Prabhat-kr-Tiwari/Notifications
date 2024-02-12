package com.example.notificationchannels

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                CHANNEL_1_ID,
                "CHANNEL 1",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = "this is channel 1 "
            channel1.enableVibration(true)
            channel1.lightColor=Color.RED

            val channel2 = NotificationChannel(
                CHANNEL_2_ID,
                "CHANNEL 2",
                NotificationManager.IMPORTANCE_LOW
            )
            channel2.description = "this is channel 2 "

            val notificationManager =
                getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }

    companion object {
        const val CHANNEL_1_ID = "channel1"
         const val CHANNEL_2_ID = "channel2"
    }
}