package com.pycogroup.duongtran.kotlinassignment.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.pycogroup.duongtran.kotlinassignment.R
import com.pycogroup.duongtran.kotlinassignment.data.api.Api
import com.pycogroup.duongtran.kotlinassignment.data.api.RetrofitClient
import com.pycogroup.duongtran.kotlinassignment.data.models.FriendModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailScreenFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_detail_screen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFriendInfo()
    }

    private fun getFriendInfo() {

        val friendId = arguments?.getString("id")

        if (friendId != null) {
            val retrofit = RetrofitClient.getInstance()
            val service = retrofit.create(Api::class.java)
            val request = service.getFriendInfo(id = friendId)

            request.enqueue(object : Callback<FriendModel> {

                override fun onFailure(call: Call<FriendModel>, error: Throwable) {
                    Log.d("onFailure", error.toString())
                    Toast.makeText(context, "Getting friend data failed", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<FriendModel>, response: Response<FriendModel>) {
                    if (response.isSuccessful) {
                        val friendInfo = response.body()
                        if (friendInfo != null) {
                            tv_name.text = friendInfo.name
                            Picasso.get().load(friendInfo.avatar_url).into(img_avatar)
                        } else {
                            Log.d("onFailure", response.toString())
                            Toast.makeText(context, "Getting friend data failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}
