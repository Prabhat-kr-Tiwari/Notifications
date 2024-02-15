package com.example.notificationchannels

//import android.app.RemoteInput

import android.Manifest
import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private lateinit var editTextTitle: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonChannel1: Button
    private lateinit var buttonChannel2: Button
    private lateinit var mediaSessionCompat: MediaSessionCompat


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        mediaSessionCompat = MediaSessionCompat(this, "tag")


        MESSAGES.add(Message("Good Morning", "Prabhat"))
        MESSAGES.add(Message("Good Morning", null))
        MESSAGES.add(Message("oye", "Akash"))
        MESSAGES.add(Message("Good Morning Prabhat", "Alexa"))


        //send on channel 1
        /*      buttonChannel1.setOnClickListener {
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

                  //input field of notification
                  val remoteInput = RemoteInput.Builder("key_text_reply")
                      .setLabel("Your Answer.....")
                      .build()

                  //direct reply receiver you can change name
                  val replyIntent = Intent(this, NotificationReceiver::class.java)
                  val replyPendingIntent = PendingIntent
                      .getBroadcast(this, 0, replyIntent, PendingIntent.FLAG_IMMUTABLE)

                  val replyAction = NotificationCompat
                      .Action.Builder(R.drawable.send, "Reply", replyPendingIntent)
                      .addRemoteInput(remoteInput).build()


                  val sender = Person.Builder()
                      .setName("Me")
                      .setIcon(IconCompat.createWithBitmap(BitmapFactory.decodeResource(resources,R.drawable.image)))
                      .build()
                  val messagingStyle = NotificationCompat.MessagingStyle(sender)
                  messagingStyle.conversationTitle = "Group Chat"


                  for (chatMessage in MESSAGES) {
                      val notificationMessage=NotificationCompat.MessagingStyle.Message(
                          chatMessage.text,
                          chatMessage.timestamp,
                          chatMessage.sender
                      )
                      messagingStyle.addMessage(notificationMessage)

                  }


                  //adding the large icon
      //            val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.image)


                  //big picture


                  val notification = NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                      .setSmallIcon(R.drawable.baseline_notifications)
                      .setStyle(messagingStyle)
                      .addAction(replyAction)
                      .setColor(Color.GREEN)


                      .setPriority(NotificationCompat.PRIORITY_HIGH)
                      .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                      .setContentIntent(contentIntent)
                      .setColor(Color.RED)
                      .setAutoCancel(true)
                      .setOnlyAlertOnce(true)
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


              }*/
        buttonChannel1.setOnClickListener {



            sendChannel1Notification(this)
        }
        //send on channel 2
        buttonChannel2.setOnClickListener {
            val title = editTextTitle.text.toString()
            val message = editTextMessage.text.toString()

            //

            val artWork = BitmapFactory.decodeResource(resources, R.drawable.image)


            val notification = NotificationCompat.Builder(this, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.baseline_paid)
                .setContentTitle(title)
                .setLargeIcon(artWork)
                .addAction(R.drawable.heart, "like", null)
                .addAction(R.drawable.caret_left, "Previous", null)
                .addAction(R.drawable.pause_circle, "Pause", null)
                .addAction(R.drawable.caret_right, "Nextx", null)
                .addAction(R.drawable.heartbreak, "Dislike", null)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mediaSessionCompat.sessionToken)


                )
                .setSubText("Sub Text")
                .addAction(R.drawable.baseline_notifications, "dislike", null)
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

    companion object {


        val MESSAGES = mutableListOf<Message>()

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        fun sendChannel1Notification(
            context: Context
        ) {
            Log.d("ALEXA", "sendChannel1Notification: $MESSAGES")


            //
            val activityIntent = Intent(context, MainActivity::class.java)
            val contentIntent = PendingIntent.getActivity(

                context,
                0,
                activityIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            //input field of notification
            val remoteInput = RemoteInput.Builder("key_text_reply")
                .setLabel("Your Answer.....")
                .build()

            //direct reply receiver you can change name
            val replyIntent = Intent(context, NotificationReceiver::class.java)


            val flag =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                    PendingIntent.FLAG_MUTABLE
                else
                    0

            val replyPendingIntent = PendingIntent
                .getBroadcast(context, 0, replyIntent, flag)

            val replyAction = NotificationCompat
                .Action.Builder(R.drawable.send, "Reply", replyPendingIntent)
                .addRemoteInput(remoteInput).build()


            val sender = Person.Builder()
                .setName("Me")
                .build()
            val messagingStyle = NotificationCompat.MessagingStyle(sender)

            messagingStyle.conversationTitle = "Group Chat"


            for (chatMessage in MESSAGES) {
                val notificationMessage = NotificationCompat.MessagingStyle.Message(
                    chatMessage.text,
                    chatMessage.timestamp,
                    chatMessage.sender
                )
                messagingStyle.addMessage(notificationMessage)

            }


            //adding the large icon
//            val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.image)


            //big picture


            val notification = NotificationCompat.Builder(context, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.baseline_notifications)
                .setStyle(messagingStyle)
                .addAction(replyAction)
                .setColor(Color.GREEN)

                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setColor(Color.RED)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build()
//
            val notificationManagerCompat=NotificationManagerCompat.from(context)
            if (ActivityCompat.checkSelfPermission(
                    context,
                    arrayOf( POST_NOTIFICATIONS).toString()
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//                return
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(POST_NOTIFICATIONS),
                    101
                )
            }
            notificationManagerCompat.notify(1, notification)


        }
    }


}