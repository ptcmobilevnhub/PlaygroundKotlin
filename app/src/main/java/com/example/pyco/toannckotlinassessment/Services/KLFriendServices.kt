package com.example.pyco.toannckotlinassessment.Services

import com.example.pyco.toannckotlinassessment.Data.*
import retrofit2.Call
import retrofit2.http.GET

interface KLFriendServices {
    @GET("friends")
    fun getFriendList(): Call<List<KLFriend>>
}