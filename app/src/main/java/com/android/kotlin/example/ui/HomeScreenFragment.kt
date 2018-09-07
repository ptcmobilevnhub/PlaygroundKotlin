package com.android.kotlin.example.ui

import android.os.Bundle
import android.view.View
import com.android.kotlin.example.R
import kotlinx.android.synthetic.main.home_screen_fragment.*

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class HomeScreenFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.home_screen_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_welcome.text = arguments?.getString("name", "Please Login")
    }
}