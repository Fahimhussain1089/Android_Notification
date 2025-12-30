package com.hussain.myapplication.notificationButton



import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.hussain.myapplication.TestingMethode

//
//class NotificationActionReceiver : BroadcastReceiver() {
//    @SuppressLint("ServiceCast")
//    override fun onReceive(context: Context, intent: Intent) {
//        when (intent.action) {
////            "TOSEE_ACTION" -> {
////                val title = intent.getStringExtra("title")
////                val body = intent.getStringExtra("body")
////                Toast.makeText(context, "Tosee: $title - $body", Toast.LENGTH_SHORT).show()
////                // Optionally, start an activity or perform an action
////            }
//            "CANCEL_ACTION" -> {
//                val notificationId = intent.getIntExtra("notificationId", -1)
//                if (notificationId != -1) {
//                    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                    notificationManager.cancel(notificationId)
//                }
//                Toast.makeText(context, "Cancel action triggered", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}
//class NotificationActionReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        when (intent.action) {
//            "CANCEL_ACTION" -> {
//                val notificationId = intent.getIntExtra("notificationId", -1)
//                if (notificationId != -1) {
//                    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                    notificationManager.cancel(notificationId)
//                    TestingMethode()
//                    Log.d("NotificationAction", "Notification with ID $notificationId canceled")
//                }
//                Toast.makeText(context, "Cancel action triggered", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}