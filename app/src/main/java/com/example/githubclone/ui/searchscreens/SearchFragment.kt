package com.example.githubclone.ui.searchscreens

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubclone.ui.MainActivity
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchBinding

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