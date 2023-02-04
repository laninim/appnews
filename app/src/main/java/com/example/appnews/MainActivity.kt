package com.example.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigationController()
    }


    private fun setupBottomNavigationController(){
        val navHost = supportFragmentManager.findFragmentById(R.id.default_nav_host) as NavHostFragment
        val navController = navHost.navController
        val bottomNavigation = this@MainActivity.findViewById<BottomNavigationView>(R.id.bottomnavigation)
        bottomNavigation.setupWithNavController(navController)
    }
}