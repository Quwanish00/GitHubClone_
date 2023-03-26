package com.example.github.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.github.R
import com.example.github.databinding.FragmentRepositoriesByReponameBinding
import com.example.github.persentation.SearchViewModel
import com.example.github.ui.adapters.ReposByReposNameAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchByRepositoryNameFragment:Fragment(R.layout.fragment_repositories_by_reponame) {
    private lateinit var binding: FragmentRepositoriesByReponameBinding
    private var adapter = ReposByReposNameAdapter()
    lateinit var viewModel: SearchViewModel
    private val navArgs: SearchByRepositoryNameFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRepositoriesByReponameBinding.bind(view)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(SearchViewModel::class.java)

        binding.apply {
            recyclerview.adapter = adapter

            recyclerview.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), DividerItemDecoration.VERTICAL
                )
            )

            back.setOnClickListener {
                findNavController().popBackStack()
            }
            initObservers()

            lifecycleScope.launchWhenResumed {
                viewModel.searchRepositoriesByRepositoryName(navArgs.searchName)
            }


        }
    }
    private fun initObservers() {
        viewModel.searchRepositoriesByRepositoryNameFlow.onEach {
            if (it.isEmpty()) {
                binding.empty.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }
}