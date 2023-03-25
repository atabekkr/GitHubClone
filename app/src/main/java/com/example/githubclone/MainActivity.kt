package com.example.githubclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.githubclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = this.supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?

        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }

        binding.bnvMain.setupWithNavController(navController)
    }
    fun visibilityOfBottomNavigation(visibility: Int) {
        binding.bnvMain.visibility = visibility
    }
}