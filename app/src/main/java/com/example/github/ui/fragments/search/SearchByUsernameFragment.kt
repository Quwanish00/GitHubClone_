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
    lateinit var viewModel: SearchViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchByPeopleBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(SearchViewModel::class.java)
        binding.apply {
            recycle.adapter = adapter
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        initObservers()

        lifecycleScope.launchWhenResumed {
            viewModel.searchUsersByUsername(navArgs.searchName)
        }


    }

    fun initObservers() {
        viewModel.searchUsersByUsernameFlow.onEach {
            if (it.isEmpty()) {
                binding.empty.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }

    }