package com.hussain.myapplication.screen

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import com.hussain.myapplication.R
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig
import java.util.Random

//
class CallActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        val notificationId = intent.getIntExtra("notificationId", -1)

        val tvCallDetails = findViewById<TextView>(R.id.tv_call_details)
        tvCallDetails.text = "Calling: $title\n$body"

        // Simulate ending the call after 10 seconds (optional)
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            if (notificationId != -1) {
                val notificationManager = getSystemService(NotificationManager::class.java)
                notificationManager.cancel(notificationId)
                Log.d("CallActivity", "Notification with ID $notificationId canceled")
            }
        }, 10000)

        // Enable back navigation to end the call
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

//
//class CallActivity : AppCompatActivity() {
//    private lateinit var rtcEngine: RtcEngine
//    private lateinit var mediaPlayer: MediaPlayer
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_call)
//
//        val callerName = intent.getStringExtra("title") ?: "Unknown Caller"
//        val callId = intent.getStringExtra("call_id") ?: return
//        val notificationId = intent.getIntExtra("notificationId", 0)
//
//        findViewById<TextView>(R.id.tv_caller_name).text = callerName
//        findViewById<Button>(R.id.btn_accept).setOnClickListener {
//            startCall(callId)
//            stopRinging()
//            // Optionally keep the activity open for call UI
//        }
//        findViewById<Button>(R.id.btn_decline).setOnClickListener {
//            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.cancel(notificationId)
//            stopRinging()
//            finish()
//        }
//
//        startRinging()
//        initializeAgora()
//    }
//
//    private fun initializeAgora() {
//        try {
//            // Initialize Agora RtcEngine
//            RtcEngineConfig().apply {
//                mContext = applicationContext
//                mAppId = "YOUR_AGORA_APP_ID" // Replace with your Agora App ID
//                mEventHandler = object : IRtcEngineEventHandler() {
//                    override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
//                        Log.d("Agora", "Joined channel $channel with UID $uid")
//                    }
//                    override fun onUserJoined(uid: Int, elapsed: Int) {
//                        Log.d("Agora", "User $uid joined")
//                    }
//                    override fun onUserOffline(uid: Int, reason: Int) {
//                        Log.d("Agora", "User $uid offline, reason: $reason")
//                        finish() // End call if other user leaves
//                    }
//                }
//            }.let { config ->
//                rtcEngine = RtcEngine.create(config)
//                rtcEngine.enableAudio() // Enable audio for voice call
//                // rtcEngine.enableVideo() // Uncomment for video call
//            }
//        } catch (e: Exception) {
//            Log.e("Agora", "Error initializing Agora: ${e.message}")
//            finish()
//        }
//    }
//
//    private fun startCall(callId: String) {
//        // Request permissions for audio (and video if needed)
//        val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions, 2)
//        }
//
//        // Join Agora channel with a random user ID
//        val userId = Random().nextInt() // Ensure this is an Int
//        rtcEngine.joinChannel(null, callId, userId, object : IRtcEngineEventHandler() {
//            override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
//                Log.d("Agora", "Joined channel $channel with UID $uid")
//            }
//        })
//    }
//
//    private fun startRinging() {
//        mediaPlayer = MediaPlayer.create(this, R.raw.incoming_call).apply {
//            isLooping = true
//            start()
//        }
//    }
//
//    private fun stopRinging() {
//        if (::mediaPlayer.isInitialized) {
//            mediaPlayer.release()
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        stopRinging()
//        if (::rtcEngine.isInitialized) {
//            rtcEngine.leaveChannel()
//            RtcEngine.destroy()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 2 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.d("Permission", "Audio permission granted")
//        } else {
//            Log.e("Permission", "Audio permission denied")
//            finish()
//        }
//    }
//}