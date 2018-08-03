package com.android.kotlin.example.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.kotlin.example.R
import kotlinx.android.synthetic.main.login_screen_fragment.*

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class LoginScreenFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.login_screen_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_go_to_login.setOnClickListener { button ->
            val userName = edt_username.text.toString().trim()
            val pwd = edt_password.text.toString().trim()

            if(userName.equals("pyco", true) && pwd.equals("123")){
                // go to home screen
                Navigation.findNavController(button).navigate(R.id.homeScreenFragment)
            } else {
                // show error log
                Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}