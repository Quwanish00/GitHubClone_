package com.example.github.ui.fragments.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.databinding.FragmentProfileBinding
import com.example.github.persentation.UserViewModel
import com.example.github.ui.adapters.RepoByProfilAdapter
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
        initObserver()
        lifecycleScope.launchWhenResumed {
            viewModel.getUserProfileInfo()
            viewModel.getUserRepositories()
        }



    }

    private fun initData() {
        adapter = RepoByProfilAdapter()
        binding.recyclerview.adapter = adapter
    }

    fun initObserver() {
        viewModel.getUserProfileInfoFlow.onEach {

            binding.apply {
                Glide.with(profil)
                    .load(it.avatar_url)
                    .into(profil)
                nik.text = it.login
                followers.text = getString(R.string.followers, it.followers)
                follower.text = getString(R.string.follower, it.following)
                repositoryCount.text = it.public_repos.toString()
            }

        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}