package com.pycogroup.duongtran.kotlinassignment.ui

import android.app.AlertDialog
import android.os.Build
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

    lateinit var mAlertBuilder: AlertDialog.Builder

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail_screen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFriendInfo()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAlertBuilder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            mAlertBuilder = AlertDialog.Builder(context)
        }
        mAlertBuilder = AlertDialog.Builder(context).setTitle("Error").setMessage("Getting friend info failed, please try again")
                .setPositiveButton("Try again", { dialog, i ->
                    getFriendInfo()
                })
                .setNegativeButton("Cancel", { dialog, i -> })
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
                    mAlertBuilder.show()
                }

                override fun onResponse(call: Call<FriendModel>, response: Response<FriendModel>) {
                    if (response.isSuccessful) {
                        val friendInfo = response.body()
                        if (friendInfo != null) {
                            tv_name.text = friendInfo.name
                            Picasso.get().load(friendInfo.avatar_url).into(img_avatar)
                        } else {
                            Log.d("onFailure", response.toString())
                            mAlertBuilder.show()
                        }
                    }
                }
            })
        }
    }
}
