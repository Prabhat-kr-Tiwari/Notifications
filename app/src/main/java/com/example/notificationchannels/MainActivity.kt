package com.example.notificationchannels

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private lateinit var editTextTitle:EditText
    private lateinit var editTextMessage:EditText
    private lateinit var buttonChannel1:Button
    private lateinit var buttonChannel2:Button
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
        buttonChannel1=findViewById(R.id.btn_channel1)
        buttonChannel2=findViewById(R.id.btn_channel2)
        editTextTitle=findViewById(R.id.edit_text_title)
        editTextMessage=findViewById(R.id.edit_text_message)
        notificationManagerCompat=NotificationManagerCompat.from(this)


        buttonChannel1.setOnClickListener {
            val title=editTextTitle.text.toString()
            val message=editTextMessage.text.toString()


            val notification=NotificationCompat.Builder(this,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.baseline_notifications)
                .setContentTitle(title)

                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
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
            notificationManagerCompat.notify(1,notification)


        }
        buttonChannel2.setOnClickListener {
            val title=editTextTitle.text.toString()
            val message=editTextMessage.text.toString()


            val notification=NotificationCompat.Builder(this,App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.baseline_paid)
                .setContentTitle(title)
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
            notificationManagerCompat.notify(2,notification)

        }

    }
}