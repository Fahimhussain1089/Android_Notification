package com.hussain.myapplication

import android.content.Context
import android.util.Log


import com.google.auth.oauth2.GoogleCredentials
import java.io.InputStream

object AccessTokenUtil {
    fun getAccessToken(context: Context): String {
        try {
            val inputStream: InputStream = context.resources.openRawResource(R.raw.myapplication)
            val credentials = GoogleCredentials
                .fromStream(inputStream)
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
            credentials.refreshIfExpired()

            val token = credentials.accessToken.tokenValue
            Log.d("FCM", "Access token generated: $token")

            return credentials.accessToken.tokenValue
        } catch (e: Exception) {
            throw RuntimeException("Failed to load access token: ${e.message}")
        }
    }
}
