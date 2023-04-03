package com.example.github.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.github.R
import com.example.github.databinding.FragmentHomeMainBinding
import com.example.github.databinding.FragmentMyWorkBinding
import com.example.github.ui.fragments.homeContainer.HomeContainerDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.FlowPreview

class HomeFragment : Fragment(R.layout.fragment_my_work) {
    lateinit var navController: NavController
  private  lateinit var binding: FragmentMyWorkBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyWorkBinding.bind(view)
        initData()
        initListeners()
    }


    private fun initData() {
        navController = Navigation.findNavController(requireActivity(), R.id.main_container)
    }


    private fun initListeners() {
        binding.apply {

            repos.setOnClickListener {
                navController.navigate(
                   HomeFragmentDirections.actionHomeFragmentToAllRepositoriesFragment()
                )
            }

            search.setOnClickListener {
                navController.navigate(
                    HomeContainerDirections.actionHomeContainerToSearchFragment()
                )
            }
        }
    }
}