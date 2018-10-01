package com.pycogroup.duongtran.kotlinassignment.data.api

import com.pycogroup.duongtran.kotlinassignment.data.models.FriendModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET(MODULE_FRIENDS)
    fun getFriendList(): Call<List<FriendModel>>
    @GET("$MODULE_FRIENDS/{id}")
    fun getFriendInfo(@Path("id") id : String): Call<FriendModel>
}