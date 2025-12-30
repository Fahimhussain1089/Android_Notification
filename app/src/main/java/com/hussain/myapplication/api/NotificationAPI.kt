package com.hussain.myapplication.api
import com.hussain.myapplication.model.Notification
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

object NotificationAPI {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fcm.googleapis.com/") // Base URL
        .addConverterFactory(GsonConverterFactory.create()) // Gson for serialization
        .build()

    val service: NotificationInterface by lazy {
        retrofit.create(NotificationInterface::class.java)
    }
}
