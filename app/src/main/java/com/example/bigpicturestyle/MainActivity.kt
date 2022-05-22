package com.example.bigpicturestyle

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.time.Instant
import java.util.*

class MainActivity : AppCompatActivity() {

    var CHANNEL_ID_ANDROID = "com.darshankomu.kotlinforandroidexamples.ANDROID"
    var CHANNEL_NAME = "ANDROID_CHANNEL"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val picNotification = findViewById<Button>(R.id.pictureNotification)
        picNotification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                var notificationBuilder:Notification.Builder? = null

                val imp = NotificationManager.IMPORTANCE_HIGH
                val mNotificationChannel = NotificationChannel(CHANNEL_ID_ANDROID,CHANNEL_NAME,imp)
                val bitmap = BitmapFactory.decodeResource(resources,R.drawable.navback)
//                val intent = Instant(this,BigPictureNotification::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(this,Calendar.getInstance().timeInMillis.toInt(),intent,0)
                notificationBuilder = Notification.Builder(this,CHANNEL_ID_ANDROID)
                    .setContentTitle("Big Picture Notification...")
                    .setContentText("This is Big Picture Style Notification...")
                    .setSmallIcon(R.drawable.kotlin)
                    .setStyle(Notification.BigPictureStyle(notificationBuilder).bigPicture(bitmap))
                    .addAction(R.drawable.kotlin,"Show Activity",pendingIntent)
                val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mNotificationChannel)
                notificationManager.notify(0,notificationBuilder.build())

            }
            else
            {
                val bitmap = BitmapFactory.decodeResource(resources,R.drawable.navback)
                val bigPicNotification:NotificationCompat.BigPictureStyle = NotificationCompat.BigPictureStyle()
                bigPicNotification.bigPicture(bitmap).build()
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                val intent = Instant(this,BigPictureNotification::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(this,Calendar.getInstance().timeInMillis.toInt(),intent,0)
                val notificationBuilder = NotificationCompat.Builder(this)
                    .setContentTitle("Big Picture Notification...")
                    .setContentText("This is Big Picture Style Notification")
                    .setSmallIcon(R.drawable.kotlin)
                    .setStyle(bigPicNotification)
                    .addAction(R.drawable.kotlin,"Show Activity",pendingIntent)
                notificationManager.notify(0,notificationBuilder.build())
            }
        }
    }
}