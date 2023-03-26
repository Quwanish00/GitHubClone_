package com.example.github.ui.fragments.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.databinding.FragmentSearchRepositoriesByTypeBinding

class SearchFragment :Fragment(R.layout.fragment_search_repositories_by_type){

    private lateinit var binding: FragmentSearchRepositoriesByTypeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchRepositoriesByTypeBinding.bind(view)


        binding.apply {

            back.setOnClickListener {
                findNavController().popBackStack()
            }

            searchView.addTextChangedListener {
                val value = searchView.text.toString()
                if(value.isEmpty()){
                    linear1.visibility = View.INVISIBLE
                }
                else{
                    empty.visibility = View.INVISIBLE
                    linear1.visibility = View.VISIBLE
                    reposs.text = getString(R.string.repositories, value)
                    peopless.text = getString(R.string.peoples, value)
                }






                peoples.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchByUsernameFragment(value)
                    )
                }

                repositories.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToSearchByRepositoryNameFragment(value)
                    )
                }
            }

        }
    }
    }
