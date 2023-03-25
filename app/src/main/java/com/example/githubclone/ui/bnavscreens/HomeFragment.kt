package com.example.githubclone.ui.bnavscreens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.githubclone.ui.MainActivity
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentHomeBinding
import com.example.githubclone.presentation.MainViewModel
import com.example.githubclone.ui.nestedgraph.ContainerHomeFragmentDirections
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val navController = Navigation.findNavController(requireActivity(), R.id.fragment_container)

        binding.apply {

            llRepo.setOnClickListener {
                navController.navigate(
                    ContainerHomeFragmentDirections.actionContainerHomeFragmentToRepositoriesFragment()
                )
            }

            ivSearch.setOnClickListener {
                navController.navigate(
                    ContainerHomeFragmentDirections.actionContainerHomeFragmentToSearchFragment()
                )
            }
        }
    }
}