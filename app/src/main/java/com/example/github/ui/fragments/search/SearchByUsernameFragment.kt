package com.example.github.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.github.R
import com.example.github.databinding.FragmentSearchByPeopleBinding
import com.example.github.persentation.SearchViewModel
import com.example.github.ui.adapters.RepositoryByUsernameAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchByUsernameFragment:Fragment(R.layout.fragment_search_by_people) {
    private lateinit var binding: FragmentSearchByPeopleBinding
    private val adapter = RepositoryByUsernameAdapter()
    private val navArgs:SearchByUsernameFragmentArgs by navArgs()
    private val viewModel by viewModel<SearchViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchByPeopleBinding.bind(view)
        binding.apply {
            recycle.adapter = adapter
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }


        lifecycleScope.launchWhenResumed {
            viewModel.searchUsersByUsername(navArgs.searchName)
        }
        initObservers()









    }

    fun initObservers() {
        viewModel.searchUsersByUsernameFlow.onEach {

                adapter.submitList(it)

        }.launchIn(lifecycleScope)
    }

    }
