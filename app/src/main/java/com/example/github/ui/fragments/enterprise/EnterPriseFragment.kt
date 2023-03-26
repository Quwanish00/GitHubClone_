package com.example.github.ui.fragments.enterprise

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.github.R
import com.example.github.databinding.FragmentEnterpriseBinding

class EnterPriseFragment :Fragment(R.layout.fragment_enterprise) {
    private lateinit var binding: FragmentEnterpriseBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterpriseBinding.bind(view)

    }
}