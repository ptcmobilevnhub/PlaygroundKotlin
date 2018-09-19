package com.android.kotlin.example.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.kotlin.example.R

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId() : Int

    protected fun getNavController(): NavController {
        return Navigation.findNavController(activity!!, R.id.navigation_host_fragment)
    }
}