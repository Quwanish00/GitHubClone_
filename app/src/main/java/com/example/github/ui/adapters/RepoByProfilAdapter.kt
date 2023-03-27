package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.data.models.GetUserProfileInfoData
import com.example.github.data.models.GetUserRepositoriesData
import com.example.github.data.models.ItemsRepoData
import com.example.github.databinding.ItemRepositoryBinding

class RepoByProfilAdapter: ListAdapter<GetUserRepositoriesData,RepoByProfilAdapter.RepositoryProfileViewHolder>(diffCallBack){
        inner class RepositoryProfileViewHolder(private val binding: ItemRepositoryBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind() {

                val d = getItem(absoluteAdapterPosition)

                binding.apply {
                    Glide.with(profil)
                        .load(d.owner.avatar_url)
                        .into(profil)
                    username.text = d.owner.login
                    starCount.text = d.stargazers_count.toString()
                    language.text = d.language
                    projectName.text = d.name

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryProfileViewHolder {
            return RepositoryProfileViewHolder(
                ItemRepositoryBinding.bind(
                    LayoutInflater.from(
                        parent.context
                    ).inflate(R.layout.item_repository, parent, false)
                )
            )
        }

        override fun onBindViewHolder(holder: RepositoryProfileViewHolder, position: Int) {
            holder.bind()
        }

        private object diffCallBack : DiffUtil.ItemCallback<GetUserRepositoriesData>() {
            override fun areItemsTheSame(
                oldItem: GetUserRepositoriesData,
                newItem: GetUserRepositoriesData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GetUserRepositoriesData,
                newItem: GetUserRepositoriesData
            ): Boolean {
                return oldItem == newItem
            }

        }
}