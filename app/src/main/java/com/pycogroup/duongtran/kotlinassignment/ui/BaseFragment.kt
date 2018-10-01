package com.pycogroup.duongtran.kotlinassignment.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pycogroup.duongtran.kotlinassignment.R

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId() : Int

    protected fun getNavController(): NavController {
        return Navigation.findNavController(activity!!, R.id.navigation_host_fragment)
    }
}