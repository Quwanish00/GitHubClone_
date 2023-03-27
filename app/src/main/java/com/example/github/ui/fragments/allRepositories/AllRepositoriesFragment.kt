package com.example.github.ui.fragments.allRepositories

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentRepositoriesBinding
import com.example.github.persentation.UserViewModel
import com.example.github.ui.adapters.AllRepositoryAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllRepositoriesFragment :Fragment(R.layout.fragment_repositories) {
    private lateinit var binding: FragmentRepositoriesBinding
    private val adapter = AllRepositoryAdapter()
    private val viewModel by viewModel<UserViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRepositoriesBinding.bind(view)
        binding.recycler.adapter = adapter

        binding.back.setOnClickListener {
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

            Log.d("Repolar","$it")
        }.launchIn(lifecycleScope)
    }
    }
