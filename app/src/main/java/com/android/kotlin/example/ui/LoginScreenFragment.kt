package com.android.kotlin.example.ui

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.kotlin.example.R
import com.android.kotlin.example.data.GithubApi
import com.android.kotlin.example.data.RetrofitClient
import com.android.kotlin.example.data.UserResponse
import kotlinx.android.synthetic.main.login_screen_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class LoginScreenFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.login_screen_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val userName = edt_username.text.toString().trim()
            val pwd = edt_password.text.toString().trim()

            login(userName, pwd)
        }

        btn_guest.setOnClickListener {
            getNavController().navigate(R.id.action_Login_To_Home)
        }
    }

    private fun login(userName: String, password: String) {
        val auth = "$userName:$password";
        val authentication = "Basic " + Base64.encodeToString(auth.toByteArray(), Base64.NO_WRAP)

        val loginService = RetrofitClient().getInstance().create(GithubApi::class.java)
        val request = loginService.login(authentication)
        request.enqueue(object : Callback<UserResponse> {

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "onFailure " + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                Log.d("TEST", ">>> : response.isSuccessful " + response.isSuccessful);

                if(response != null && response.isSuccessful){
                    val userResponse = response.body()
                    val arg = Bundle()
                    arg.putString("name", userResponse?.name)
                    getNavController().navigate(R.id.action_Login_To_Home, arg)
                }
            }
        })
    }

    private fun getNavController(): NavController {
        return Navigation.findNavController(activity!!, R.id.navigation_host_fragment)
    }
}