package com.pycogroup.duongtran.kotlinassignment.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.AlertDialogLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.pycogroup.duongtran.kotlinassignment.R
import com.pycogroup.duongtran.kotlinassignment.data.api.Api
import com.pycogroup.duongtran.kotlinassignment.data.api.RetrofitClient
import com.pycogroup.duongtran.kotlinassignment.data.models.FriendModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.fragment_recyleview_item_friend.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeScreenFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_home_screen

    var mFriendListAdapter : FriendListAdapter? = null
    lateinit var mAlertBuilder: AlertDialog.Builder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAlertBuilder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            mAlertBuilder = AlertDialog.Builder(context)
        }
        mAlertBuilder = AlertDialog.Builder(context).setTitle("Error").setMessage("Getting friend failed, please try again")
                .setPositiveButton("Try again", { dialog, i ->
                    getFriendList()
                })
                .setNegativeButton("Cancel", { dialog, i -> })


        recycle_view.layoutManager = LinearLayoutManager(context)

        mFriendListAdapter = FriendListAdapter()
        recycle_view.adapter = mFriendListAdapter

        getFriendList()
    }

    private fun getFriendList() {
        val retrofit = RetrofitClient.getInstance()
        val service = retrofit.create(Api::class.java)
        val request = service.getFriendList()

        request.enqueue(object : Callback<List<FriendModel>> {

            override fun onFailure(call: Call<List<FriendModel>>, error: Throwable) {
                Log.d("onFailure", error.toString())
                mAlertBuilder.show()
            }

            override fun onResponse(call: Call<List<FriendModel>>, response: Response<List<FriendModel>>) {
                if(response.isSuccessful){
                    val friendList = response.body()
                    if (friendList != null) {
                        mFriendListAdapter!!.setItemList(friendList)
                    } else {
                        Log.d("onFailure", response.toString())
                        mAlertBuilder.show()
                    }
                }
            }
        })
    }

    class FriendListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var mFriend : List<FriendModel> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return FriendViewHolder(parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if(holder is FriendViewHolder){
                holder.bind(mFriend[position])
            }
        }

        override fun getItemCount(): Int {
            return mFriend.size
        }

        fun setItemList(friend: List<FriendModel>) {
            mFriend = friend
            notifyDataSetChanged()
        }

    }

    class FriendViewHolder : RecyclerView.ViewHolder {

        companion object {
            fun getView(parent: ViewGroup): View {
                return LayoutInflater.from(parent?.context).inflate(R.layout.fragment_recyleview_item_friend, null)
            }
        }

        constructor(parent: ViewGroup) : super(getView(parent))

        fun bind(friendData: FriendModel?) {
            itemView.tv_name.text = friendData?.name
            if(friendData?.avatar_url != null) {
                Picasso.get().load(friendData.avatar_url).into(itemView.img_avatar)
            }
            itemView.view_parent.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", friendData?.id)

                Navigation.findNavController(it).navigate(R.id.action_homeScreenFragment_to_detailScreenFragment, bundle)
            }
        }
    }


}
