package com.example.githubclone.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.data.local.LocalStorage
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.fragment_splash){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {

            delay(1000)
            if (LocalStorage().isLogin) {
                Log.d("TTTT", "ksajdfksjfio")
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToContainerHomeFragment())
            } else {
                Log.d("TTTT", "is logine5")
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFirstFragment())
            }
        }
    }
}