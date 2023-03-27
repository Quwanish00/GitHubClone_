package com.example.github.ui.fragments.allRepositories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentRepositoriesInProfileBinding
import com.example.github.persentation.UserViewModel
import com.example.github.ui.adapters.RepoProfilByRepoNameAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRepositoriesInProfileFragment :Fragment(R.layout.fragment_repositories_in_profile) {
    private lateinit var binding: FragmentRepositoriesInProfileBinding
    private val viewModel by viewModel<UserViewModel>()
    private val adapter = RepoProfilByRepoNameAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRepositoriesInProfileBinding.bind(view)
        binding.recyclerview.adapter =adapter
        initObservers()
        binding.apply {
            lifecycleScope.launchWhenResumed {
                viewModel.getUserRepositories()
            }

        }


        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }




    }

    private fun initObservers() {
        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }



    }
