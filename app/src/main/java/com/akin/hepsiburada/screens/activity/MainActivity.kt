package com.akin.hepsiburada.screens.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    private lateinit var drawer: DrawerLayout
    private lateinit var container: ConstraintLayout
    private lateinit var myToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        //nav logic

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

         navController = navHostFragment.navController

        //bottom nav logic
        val bottomNav = binding.bottomNavigationView
        bottomNav.background = null
        bottomNav.menu.getItem(2).isEnabled = false

        //drawer logic
        drawer = binding.drawerLayout
        container = binding.container

        val drawerIcon = binding.drawerMenuIcon
        drawerIcon.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
//        myToggle = ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close)
//        drawer.addDrawerListener(myToggle)
//        myToggle.syncState()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        window.navigationBarColor= Color.TRANSPARENT
//        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (myToggle.onOptionsItemSelected(item)){
//            return  true
//        }
//        return super.onOptionsItemSelected(item)
//    }

}