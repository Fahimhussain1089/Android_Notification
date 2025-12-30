package com.hussain.myapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
//import com.hussain.myapplication.notificationButton.NotificationActionReceiver
import com.hussain.myapplication.screen.CallActivity
import com.hussain.myapplication.screen.SecondScreenActivity
import java.util.Random
import java.util.jar.Manifest

//@HiltAndroidApp
//class MyFirebaseMessagingService : FirebaseMessagingService() {

//
//class MyFirebaseMessagingService : FirebaseMessagingService() {
//
//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        Log.d("FCM", "New token: $token")
//        // Send the token to your server or save it as needed
//    }
//
//    private fun showNotification(title: String?, body: String?) {
//        // Generate a unique notification ID
//        val notificationId = Random().nextInt()
//
//        // Intent for MainActivity (default content intent)
//        val intent = Intent(this, MainActivity::class.java).apply {
//            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        }
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        // Intent for "Tosee" action (navigate to SecondScreenActivity with notification ID)
//        val toseeIntent = Intent(this, SecondScreenActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            putExtra("title", title)
//            putExtra("body", body)
//            putExtra("notificationId", notificationId) // Pass the notification ID
//        }
//        val toseePendingIntent = PendingIntent.getActivity(this, 1, toseeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//
//        // Intent for "Cancel" action (broadcast to dismiss)
//        val cancelIntent = Intent(this, NotificationActionReceiver::class.java).apply {
//            action = "CANCEL_ACTION"
//            putExtra("notificationId", notificationId) // Pass the notification ID
//        }
//        val cancelPendingIntent = PendingIntent.getBroadcast(this, 2, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//
//        val channelId = "FCM_Channel"
//        val notificationBuilder = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.mipmap.ic_launcher) // Use a valid icon
//            .setContentTitle(title)
//            .setContentText(body)
//            .setAutoCancel(false) // Disable auto-cancel to handle manually
//            .setContentIntent(pendingIntent)
//            .addAction(
//                NotificationCompat.Action.Builder(
//                    IconCompat.createWithResource(this, android.R.drawable.ic_dialog_info), // Green-themed icon
//                    " Watch ",
//                    toseePendingIntent
//                ).build()
//            )
//            .addAction(
//                NotificationCompat.Action.Builder(
//                    IconCompat.createWithResource(this, android.R.drawable.ic_dialog_alert), // Red-themed icon
//                    "Cancel",
//                    cancelPendingIntent
//                ).build()
//            )
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "FCM Notifications",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        notificationManager.notify(notificationId, notificationBuilder.build()) // Use the generated ID
//    }
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//
//        // Handle notification payload
//        remoteMessage.notification?.let {
//            Log.d("FCM", "Message Notification Title: ${it.title}")
//            Log.d("FCM", "Message Notification Body: ${it.body}")
//            showNotification(it.title, it.body)
//        }
//
//        // Handle data payload
//        remoteMessage.data.isNotEmpty().let {
//            Log.d("FCM", "Message Data Payload: ${remoteMessage.data}")
//            val title = remoteMessage.data["title"]
//            val body = remoteMessage.data["body"]
//            if (title != null && body != null) {
//                showNotification(title, body)
//            }
//        }
//    }
//}
//

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New token: $token")
        // Send the token to your server or save it as needed
    }

    private fun showNotification(title: String?, body: String?, notificationId: Int) {
        // Intent for MainActivity (default content intent)
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Intent for "Watch" action (navigate to SecondScreenActivity with notification ID)
        val toseeIntent = Intent(this, SecondScreenActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("title", title)
            putExtra("body", body)
            putExtra("notificationId", notificationId) // Pass the notification ID
        }
        val toseePendingIntent = PendingIntent.getActivity(this, 1, toseeIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Intent for "Cancel" action (broadcast to dismiss)
//        val cancelIntent = Intent(this, NotificationActionReceiver::class.java).apply {
//            action = "CANCEL_ACTION"
//            putExtra("notificationId", notificationId) // Pass the notification ID
//        }
        val cancelIntent = Intent(this, NotificationActionReceiver::class.java).apply {
            action = "CANCEL_ACTION"
            putExtra("notificationId", notificationId) // Pass the notification ID
        }
//        val cancelPendingIntent = PendingIntent.getBroadcast(
//            this,
//            2,
//            cancelIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val cancelPendingIntent = PendingIntent.getBroadcast(
            this,
            notificationId, // Use notificationId as request code
            cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "FCM_Channel"

        // Create remote views for custom layout
        val remoteViews = RemoteViews(packageName, R.layout.custom_notification_layout)
        remoteViews.setTextViewText(R.id.notification_title, title)
        remoteViews.setTextViewText(R.id.notification_body, body)

        // Set click listeners for buttons
        remoteViews.setOnClickPendingIntent(R.id.btn_watch, toseePendingIntent)
        remoteViews.setOnClickPendingIntent(R.id.btn_cancel, cancelPendingIntent)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setCustomContentView(remoteViews) // Use custom view
            .setStyle(NotificationCompat.DecoratedCustomViewStyle()) // Apply system styling
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "FCM Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notificationId, notificationBuilder.build()) // Use the generated ID
    }

    // Custom layout for the notificat

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Handle notification payload (for background)
        remoteMessage.notification?.let {
            Log.d("FCM", "Message Notification Title: ${it.title}")
            Log.d("FCM", "Message Notification Body: ${it.body}")
            val notificationId = Random().nextInt()
            showNotification(it.title, it.body, notificationId)
        }

        // Handle data payload (for foreground)
        remoteMessage.data.isNotEmpty().let {
            Log.d("FCM", "Message Data Payload: ${remoteMessage.data}")
            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
            val notificationId = Random().nextInt()
            if (title != null && body != null) {
                showNotification(title, body, notificationId)
            }
        }
    }
}

class NotificationActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "CANCEL_ACTION" -> {
                val notificationId = intent.getIntExtra("notificationId", -1)
                if (notificationId != -1) {

                    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.cancel(notificationId)
                    // Call your testing method
                    TestingMethode(context)

                    Log.d("NotificationAction", "Notification with ID $notificationId canceled")
                }
                Toast.makeText(context, "Cancel action triggered", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


// Modify your TestingMethode to accept a Context parameter
fun TestingMethode(context: Context) {
    // You can show a toast, log, or make API calls here
    Toast.makeText(context, "This is cancel method for API call", Toast.LENGTH_SHORT).show()
    Log.d("TestingMethod", "Cancel method executed")

    // If you need to make an API call, you can do it here
    // For example:
    // makeApiCall()
}
