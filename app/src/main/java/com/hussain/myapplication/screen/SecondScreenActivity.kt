package com.hussain.myapplication.screen

import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hussain.myapplication.R
//
//class SecondScreenActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_second_screen)
//        val title = intent.getStringExtra("title")
//        val body = intent.getStringExtra("body")
//        val textView = findViewById<TextView>(R.id.tv_notification_details)
//        textView.text = "Title: $title \n " +
//                "Body: $body"
//
//    }
//}
class SecondScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        // Dismiss the notification
        val notificationId = intent.getIntExtra("notificationId", -1)
        if (notificationId != -1) {
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.cancel(notificationId)
            Log.d("SecondScreen", "Notification with ID $notificationId canceled")
        }

        // Display notification details
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        val textView = findViewById<TextView>(R.id.tv_notification_details)
        textView.text = "Title: $title\nBody: $body"

        // Enable back navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}