package com.example.github.ui.fragments.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.R
import com.example.github.data.local.LocalStorage
import com.example.github.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment:Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)


        if (LocalStorage().isReg) {
            lifecycleScope.launchWhenResumed {
                delay(200)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToHomeContainer()
                )
            }
        } else {
            lifecycleScope.launchWhenResumed {
                delay(200)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToMainFragment()
                )
            }
        }
    }
}