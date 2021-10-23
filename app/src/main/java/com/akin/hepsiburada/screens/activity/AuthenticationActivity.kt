package com.akin.hepsiburada.screens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager.widget.ViewPager
import com.akin.hepsiburada.R
import com.akin.hepsiburada.screens.adapters.ViewPagerAuthenticationAdapter
import com.akin.hepsiburada.screens.fragments.LoginFragment
import com.akin.hepsiburada.screens.fragments.SignUpFragment
import com.google.android.material.tabs.TabLayout

class AuthenticationActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_auth_container) as NavHostFragment

        navController = navHostFragment.navController



    }
}