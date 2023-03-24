package com.example.githubclone.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchBinding
import com.example.githubclone.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initObservers()

        binding.ivBack.setOnClickListener {
            val value = binding.etSearch.text.toString()
            if (value.isNotEmpty()) {
                lifecycleScope.launchWhenResumed {
                    viewModel.searchRepoByRepoName(value)
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.getSuccessFlow.onEach {
            Log.d("searchUsers", it)
        }.launchIn(lifecycleScope)
    }
}