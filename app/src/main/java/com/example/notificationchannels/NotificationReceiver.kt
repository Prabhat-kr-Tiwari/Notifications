package com.example.notificationchannels

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput

class NotificationReceiver:BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ALEXA", "onReceive: ")

        /*val message=intent!!.getStringExtra("toastMessage")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()*/

        val remoteInput=RemoteInput.getResultsFromIntent(intent!!)
        Log.d("ALEXA", "onReceive: ${remoteInput?.isEmpty}")
        if (remoteInput!=null){
            val input=remoteInput.getCharSequence("key_text_reply")
            Log.d("ALEXA", "onReceive: ${MainActivity.MESSAGES} ")
            val person = Person.Builder().setName("Me").build()

            val replyText=remoteInput.getCharSequence("key_text_reply")
            Log.d("ALEXA", "onReceive: $replyText")
            val answer=MyMessage(replyText,null)
//            MainActivity.MESSAGES.add(Message("ilu","tom"))
            MainActivity.MESSAGES.add(answer)

          /*  if (context != null) {
                MainActivity.sendChannel1Notification(context)
            }*/
            val message = NotificationCompat.MessagingStyle.Message(
                input, System.currentTimeMillis(), person
            )
            val notificationStyle = NotificationCompat.MessagingStyle(person).addMessage(message)

            for (chatMessage in MainActivity.MESSAGES){
                val notificationMessage = NotificationCompat.MessagingStyle.Message(
                    chatMessage.text,
                    chatMessage.timestamp,
                    chatMessage.sender
                )
                notificationStyle.addMessage(notificationMessage)
            }
            val notificationManagerCompat=NotificationManagerCompat.from(context!!)
            val notificationCompat=NotificationCompat.Builder(context,App.CHANNEL_1_ID)
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificationManagerCompat.notify(
                1,
                notificationCompat
                    .setStyle(notificationStyle)
//                    .setContentTitle("Sent!")
//                    .setStyle(null)
                    .build()
            )
            



        }



    }

}