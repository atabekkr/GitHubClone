package com.example.githubclone.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchBinding
import com.example.githubclone.presentation.MainViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding.apply {

            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            etSearch.addTextChangedListener {
                val value = etSearch.text.toString()

                tvFind.visibility = View.INVISIBLE
                tvAllOf.visibility = View.INVISIBLE

                layoutPods.visibility = View.VISIBLE

                tvRepo.text = getString(R.string.repo_text, value)
                tvPeople.text = getString(R.string.people_text, value)

                llPeople.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchByUserNameFragment(value)
                    )
                }

                llRepo.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchByRepoFragment(value)
                    )
                }
            }

        }
    }
}