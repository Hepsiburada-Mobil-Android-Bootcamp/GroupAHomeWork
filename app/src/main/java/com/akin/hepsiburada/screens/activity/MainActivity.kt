package com.akin.hepsiburada.screens.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.ActivityMainBinding
import com.akin.hepsiburada.screens.fragments.BottomSheetFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    private lateinit var drawer: DrawerLayout
    private lateinit var container: ConstraintLayout
    private lateinit var fabButton: FloatingActionButton
    private lateinit var profilePic: ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        fabButton = binding.fab

        fabButton.setOnClickListener {
            val bottomFragment = BottomSheetFragment()
            bottomFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.ThemeOverlay_Demo_BottomSheetDialog)
            bottomFragment.show(supportFragmentManager,"TAG")

        }


        //nav logic

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        //bottom nav logic
        val bottomNav = binding.bottomNavigationView
        bottomNav.background = null
        bottomNav.menu.getItem(2).isEnabled = false

        bottomNav.setupWithNavController(navController)


        //drawer logic
        drawer = binding.drawerLayout
        container = binding.container
        profilePic = binding.profilPic
        val drawerNav = binding.navigationView
        drawerNav.itemIconTintList = null
        val drawerIcon = binding.drawerMenuIcon

        drawerIcon.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
        NavigationUI.setupWithNavController(drawerNav, navController)
        val menuItem: MenuItem = drawerNav.getMenu()
            .findItem(R.id.changeTheme) // This is the menu item that contains your switch

        val drawer_switch = menuItem.getActionView().findViewById(R.id.drawer_switch) as SwitchCompat
        drawer_switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }
        //fab logic
        drawerNav.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.exit -> println("sada")


            }
            true
        }




    }




}