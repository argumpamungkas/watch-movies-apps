package com.argumpamungkas.moviesapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.argumpamungkas.moviesapps.R
import com.argumpamungkas.moviesapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navControll = findNavController(R.id.nav_host)
        binding.bottomNav.setupWithNavController(navControll)
    }
}