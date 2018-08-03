package com.android.kotlin.example.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import com.android.kotlin.example.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val hostFragment = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.navigation_host_fragment, hostFragment)
                .setPrimaryNavigationFragment(hostFragment) // this is the equivalent to app:defaultNavHost="true"
                .commit()*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.navigation_host_fragment).navigateUp()
    }
}
