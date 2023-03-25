package com.example.githubclone.ui.nestedgraph

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentContainerHomeBinding

class ContainerHomeFragment: Fragment(R.layout.fragment_container_home) {

    private lateinit var binding: FragmentContainerHomeBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContainerHomeBinding.bind(view)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()

//        binding.bnvMain.setupWithNavController(navController)
//
//        binding.bnvMain.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.homeFragment ->{
//                }
//                R.id.notification -> {
//                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToNotificationsFragment())
//                }
//                R.id.explore -> {
//                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToExploreFragment())
//                }
//                R.id.profile -> {
//                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
//                }
//            }
//            true
//        }
    }
}