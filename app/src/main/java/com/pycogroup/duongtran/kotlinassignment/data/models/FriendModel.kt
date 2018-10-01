package com.pycogroup.duongtran.kotlinassignment.data.models

import com.google.gson.annotations.SerializedName

open class FriendModel(id: String, createdAt: String, name: String, avatar_url: String) {

    @SerializedName("id")
    val id : String? = null

    @SerializedName("createdAt")
    val createdAt : String? = null

    @SerializedName("name")
    val name : String? = null

    @SerializedName("avatar_url")
    val avatar_url : String? = null

}
