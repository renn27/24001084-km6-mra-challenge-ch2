package com.rendi.foodorderapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rendi.foodorderapp.R
import com.rendi.foodorderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        val navController = findNavController(R.id.main_nav_host)
        binding.bnvMain.setupWithNavController(navController)
    }
}