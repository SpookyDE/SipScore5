package com.sweng.sipscore5

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class AppActivity : AppCompatActivity() {

    private val internetCheckInterval = 5000L // Überprüfungsintervall in Millisekunden
    private val handler = Handler()

    private val internetCheckRunnable = object : Runnable {
        override fun run() {
            checkInternetConnectionAndCloseIfUnavailable()
            handler.postDelayed(this, internetCheckInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        startInternetConnectionCheck()
    }


    override fun onDestroy() {
        super.onDestroy()
        stopInternetConnectionCheck()
        Toast.makeText(this, "Ausgeloggt", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startInternetConnectionCheck() {
        handler.postDelayed(internetCheckRunnable, internetCheckInterval)
    }

    private fun stopInternetConnectionCheck() {
        handler.removeCallbacks(internetCheckRunnable)
    }

    private fun checkInternetConnectionAndCloseIfUnavailable() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null || !networkInfo.isConnected) {
            stopInternetConnectionCheck()
            finish()
            Toast.makeText(this, "Keine Internetverbindung.", Toast.LENGTH_SHORT).show()
        }
    }
}