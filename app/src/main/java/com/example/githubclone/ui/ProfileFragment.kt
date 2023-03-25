package com.example.githubclone.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentProfileBinding
import com.example.githubclone.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        initObservers()


        lifecycleScope.launchWhenResumed {
            viewModel.getUserProfileInfo()
        }



    }

    @SuppressLint("StringFormatMatches")
    fun initObservers() {
        viewModel.getUserPrInfoSuccessFlow.onEach {

            binding.apply {
                Glide.with(profileImage)
                    .load(it.avatar_url)
                    .into(profileImage)
                tvUser.text = it.login
                tvFollowers.text = getString(R.string.tv_followers, it.followers)
                tvFollowing.text = getString(R.string.tv_following, it.following)
                countRepo.text = it.public_repos.toString()
            }
        }.launchIn(lifecycleScope)
    }
}