package com.example.github.ui.fragments.homeContainer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.github.R
import com.example.github.databinding.FragmentHomeMainBinding

class HomeContainer:Fragment(R.layout.fragment_home_main) {
    lateinit var navController: NavController
    private lateinit var binding: FragmentHomeMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeMainBinding.bind(view)


        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.btnView.setupWithNavController(navController)
    }
}