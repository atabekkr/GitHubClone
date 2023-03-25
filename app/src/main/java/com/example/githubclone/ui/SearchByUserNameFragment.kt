package com.example.githubclone.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchByUsernameBinding
import com.example.githubclone.presentation.MainViewModel
import com.example.githubclone.ui.adapters.SearchUserAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchByUserNameFragment : Fragment(R.layout.fragment_search_by_username) {

    private lateinit var binding: FragmentSearchByUsernameBinding
    private val viewModel by viewModel<MainViewModel>()
    private val navArgs: SearchByUserNameFragmentArgs by navArgs()
    private val adapter = SearchUserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByUsernameBinding.bind(view)

        binding.apply {
            recyclerView.adapter = adapter
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        initObservers()

        lifecycleScope.launchWhenResumed {
            viewModel.searchUsersByUserName(navArgs.username)
        }


    }

    fun initObservers() {
        viewModel.getSearchByUserFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}