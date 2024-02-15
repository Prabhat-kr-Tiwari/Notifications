package com.example.notificationchannels

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.RemoteInput

class NotificationReceiver:BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ALEXA", "onReceive: ")

        /*val message=intent!!.getStringExtra("toastMessage")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()*/

        val remoteInput=RemoteInput.getResultsFromIntent(intent!!)
        if (remoteInput!=null){
            Log.d("ALEXA", "onReceive: ${MainActivity.MESSAGES} ")
            val replyText=remoteInput.getCharSequence("key_text_reply")
            val answer=Message(replyText,null)
//            MainActivity.MESSAGES.add(Message("ilu","tom"))
            MainActivity.MESSAGES.add(answer)

            if (context != null) {
                MainActivity.sendChannel1Notification(context)
            }
            



        }



    }

}