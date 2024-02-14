package com.example.notificationchannels

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private lateinit var editTextTitle: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonChannel1: Button
    private lateinit var buttonChannel2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //at first ask permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(POST_NOTIFICATIONS), 101)
            }
        }
        buttonChannel1 = findViewById(R.id.btn_channel1)
        buttonChannel2 = findViewById(R.id.btn_channel2)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextMessage = findViewById(R.id.edit_text_message)
        notificationManagerCompat = NotificationManagerCompat.from(this)


        //send on channel 1
        buttonChannel1.setOnClickListener {
            val title = editTextTitle.text.toString()
            val message = editTextMessage.text.toString()


            //
            val activityIntent = Intent(this, MainActivity::class.java)
            val contentIntent = PendingIntent.getActivity(

                this,
                0,
                activityIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val broadcastIntent = Intent(this, NotificationReceiver::class.java)
            broadcastIntent.putExtra("toastMessage", message)
            val actionIntent = PendingIntent.getBroadcast(
                this,
                0,
                broadcastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )


            //adding the large icon
            val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.image)


            val notification = NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.baseline_notifications)
                .setContentTitle(title)
                .setLargeIcon(largeIcon)
                .setStyle(
                    NotificationCompat.BigTextStyle()

                        .bigText(getString(R.string.dummy_text))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text")
                )

                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setColor(Color.RED)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            notificationManagerCompat.notify(1, notification)


        }
        //send on channel 2
        buttonChannel2.setOnClickListener {
            val title = editTextTitle.text.toString()
            val message = editTextMessage.text.toString()

            //


            val notification = NotificationCompat.Builder(this, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.baseline_paid)
                .setContentTitle(title)
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine("This is line 1")
                        .addLine("This is line 2")
                        .addLine("This is line 3")
                        .addLine("This is line 4")
                        .addLine("This is line 5")
                        .addLine("This is line 6")
                        .addLine("This is line 7")
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text")


                )

                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)

                .build()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            notificationManagerCompat.notify(2, notification)

        }

    }
}