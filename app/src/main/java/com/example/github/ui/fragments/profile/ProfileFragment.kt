package com.example.github.ui.fragments.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.databinding.FragmentProfileBinding
import com.example.github.persentation.UserViewModel
import com.example.github.ui.adapters.RepoByProfilAdapter
import com.example.github.ui.fragments.homeContainer.HomeContainerDirections
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<UserViewModel>()
    private lateinit var adapter: RepoByProfilAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        initData()

        lifecycleScope.launchWhenResumed {
            viewModel.getUserProfileInfo()
            viewModel.getUserRepositoriespInProfile()
        }
        initObserver()

binding.repositoriesProfil.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAllRepositoriesInProfileFragment())
}
    }

    private fun initData() {
        adapter = RepoByProfilAdapter()
        binding.recyclerview.adapter = adapter
    }

    fun initObserver() {
        viewModel.getUserProfileInfoFlow.onEach {

            binding.apply {
                Glide.with(this@ProfileFragment)
                    .load(it.avatar_url)
                    .into(profil)
                nik.text = it.login
                followers.text = it.followers.toString()
                follower.text = it.following.toString()
                repositoryCount.text = it.public_repos.toString()
            }

        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoriesInProfileFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}