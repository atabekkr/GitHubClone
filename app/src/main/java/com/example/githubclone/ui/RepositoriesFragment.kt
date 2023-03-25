package com.example.githubclone.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentRepositoriesBinding
import com.example.githubclone.presentation.MainViewModel
import com.example.githubclone.ui.adapters.RepoAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesFragment : Fragment(R.layout.fragment_repositories) {

    private lateinit var binding: FragmentRepositoriesBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = RepoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoriesBinding.bind(view)

        binding.recyclerView.adapter = adapter

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        initObservers()

        binding.apply {
            lifecycleScope.launchWhenResumed {
                viewModel.getUserRepositories()
            }
        }
    }

    private fun initObservers() {
        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}