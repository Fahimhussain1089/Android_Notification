package com.hussain.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

import com.google.firebase.messaging.FirebaseMessaging
import com.hussain.myapplication.api.NotificationAPI
import com.hussain.myapplication.model.Notification
import com.hussain.myapplication.model.NotificationData
//import com.hussain.myapplication.notificationButton.NotificationActionReceiver
import com.hussain.myapplication.screen.CallActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnSendNotification = findViewById<Button>(R.id.btn_send_notification)
        val btnCall = findViewById<Button>(R.id.btn_call_notification)

        btnSendNotification.setOnClickListener {
            sendNotification()
        }

        btnCall.setOnClickListener {
            showIncomingCallNotification()
        }

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Dexter.withContext(applicationContext)
                .withPermission(Manifest.permission.POST_NOTIFICATIONS)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        Log.d("Permission", "Notification permission granted.")
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Log.e("Permission", "Notification permission denied.")
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                }).check()
        }

        // Request call permission (optional, if needed for future call features)
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        }

        // Set ThreadPolicy to allow network operations in main thread (only for development/testing)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Subscribe to a topic for notifications
        FirebaseMessaging.getInstance().subscribeToTopic("hussain")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FCM", "Subscribed to topic: hussain")
                } else {
                    Log.e("FCM", "Failed to subscribe to topic: ${task.exception?.message}")
                }
            }

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e("FCM", "Fetching FCM token failed", task.exception)
                    return@addOnCompleteListener
                }

                // Get the FCM token
                val token = task.result
                Log.d("FCM", "FCM Token: $token")
            }
    }

    private fun sendNotification() {
        val notification = Notification(
            message = NotificationData(
                "hussain",
                hashMapOf(
                    "title" to "This is the notification title",
                    "body" to "This is the Firebase message v1 notification"
                )
            )
        )

        try {
            val accessToken = "Bearer ${AccessTokenUtil.getAccessToken(this)}"

            NotificationAPI.service.notification(notification, accessToken).enqueue(
                object : Callback<Notification> {
                    override fun onResponse(call: Call<Notification>, response: Response<Notification>) {
                        if (response.isSuccessful) {
                            Log.d("NotificationResponse", "Success: ${response.body()}")
                            Toast.makeText(this@MainActivity, "Notification sent successfully.", Toast.LENGTH_LONG).show()
                        } else {
                            Log.e("NotificationResponse", "Error: ${response.errorBody()?.string()}")
                            Toast.makeText(this@MainActivity, "Failed: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Notification>, t: Throwable) {
                        Log.e("NotificationResponse", "Failure: ${t.message}")
                        Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                }
            )
        } catch (e: Exception) {
            Log.e("AccessTokenError", "Failed to generate access token: ${e.message}")
            Toast.makeText(this, "Error generating access token: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("ServiceCast")
    private fun showIncomingCallNotification() {
        val title = "Incoming Call"
        val body = "Call from hussain"
        val notificationId = Random.nextInt()

        // Intent for MainActivity (default content intent)
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Intent for "Accept" action (launch CallActivity with full screen)
        val acceptIntent = Intent(this, CallActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("title", title)
            putExtra("body", body)
            putExtra("notificationId", notificationId)
        }
        val acceptPendingIntent = PendingIntent.getActivity(this, 1, acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Intent for "Decline" action (broadcast to dismiss)
        val declineIntent = Intent(this, NotificationActionReceiver::class.java).apply {
            action = "CANCEL_ACTION"
            putExtra("notificationId", notificationId)
        }
        val declinePendingIntent = PendingIntent.getBroadcast(this, 2, declineIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val channelId = "FCM_Call_Channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create or update the notification channel with high importance for heads-up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "FCM Call Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(true)
                lockscreenVisibility = NotificationManager.IMPORTANCE_HIGH
                enableLights(true)
                lightColor = Color.GREEN
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 1000, 500, 1000)
                setSound(
                    Uri.parse("android.resource://${packageName}/raw/incoming_call"),
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                        .build()
                )
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Custom layout for the notification
        val customView = RemoteViews(packageName, R.layout.custom_notification_layout).apply {
            setTextViewText(R.id.tv_title, title)
            setTextViewText(R.id.tv_body, body)
            setOnClickPendingIntent(R.id.btn_watch, acceptPendingIntent) // Repurpose as Accept
            setOnClickPendingIntent(R.id.btn_cancel, declinePendingIntent) // Remain as Decline
            // Set background colors for buttons
            setInt(R.id.btn_watch, "setBackgroundColor", Color.GREEN) // Green for Accept
            setInt(R.id.btn_cancel, "setBackgroundColor", Color.RED) // Red for Decline
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setOngoing(true)
            .setTimeoutAfter(30000)
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)
            .setCustomContentView(customView)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setFullScreenIntent(acceptPendingIntent, true)

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission", "Call permission granted.")
            } else {
                Log.e("Permission", "Call permission denied.")
                Toast.makeText(this, "Call permission denied. Cannot make calls.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
