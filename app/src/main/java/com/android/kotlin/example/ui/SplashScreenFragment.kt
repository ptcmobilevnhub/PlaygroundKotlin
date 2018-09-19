package com.android.kotlin.example.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.android.kotlin.example.R
import kotlinx.android.synthetic.main.splash_screen_fragment.*

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class SplashScreenFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.splash_screen_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_go_to_login.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_splash_to_login))

        btn_go_to_login.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.loginScreenFragment)
        }
    }

}