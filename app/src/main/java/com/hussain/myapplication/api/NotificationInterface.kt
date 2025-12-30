package com.hussain.myapplication.api

import com.hussain.myapplication.model.Notification
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface NotificationInterface {
    @POST("https://fcm.googleapis.com/v1/projects/notification-73b74/messages:send")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun notification(
        @Body message: Notification,
        @Header("Authorization") accessToken: String
    ): Call<Notification>
}