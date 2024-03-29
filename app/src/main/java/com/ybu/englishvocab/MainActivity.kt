package com.ybu.englishvocab

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ybu.englishvocab.service.MyReceiver
import com.ybu.englishvocab.service.NotificationService
import com.ybu.englishvocab.service.ServiceNotification


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //register receiver for getting notification
        val intentFilter = IntentFilter()
        intentFilter.addAction("NOTIFICATION") //Should be the same action in service
        registerReceiver(MyReceiver(), intentFilter)
        /*val intent = Intent(this,NotificationService::class.java)
        startService(intent)*/
        startService(Intent(this, ServiceNotification::class.java))

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_quiz, R.id.navigation_my_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }


}