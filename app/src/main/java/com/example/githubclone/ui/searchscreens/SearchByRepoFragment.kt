package com.example.githubclone.ui.searchscreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubclone.ui.MainActivity
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchByRepoBinding
import com.example.githubclone.presentation.MainViewModel
import com.example.githubclone.ui.adapters.SearchRepoAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchByRepoFragment : Fragment(R.layout.fragment_search_by_repo) {

    private lateinit var binding: FragmentSearchByRepoBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = SearchRepoAdapter()
    private val navArgs: SearchByRepoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByRepoBinding.bind(view)

        binding.apply {
            recyclerView.adapter = adapter

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        initObservers()

        lifecycleScope.launchWhenResumed {
            viewModel.searchRepoByRepoName(navArgs.repoName)
        }
    }

    private fun initObservers() {
        viewModel.searchReposByRepoNameFlow.onEach {
            if (it.isEmpty()) {
                binding.tvNull.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }
}