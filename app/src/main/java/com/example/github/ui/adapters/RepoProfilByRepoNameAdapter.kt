package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.data.models.GetUserRepositoriesData

import com.example.github.databinding.ItemRepositoryInProfileBinding

class RepoProfilByRepoNameAdapter:
ListAdapter<GetUserRepositoriesData, RepoProfilByRepoNameAdapter.RepositoryViewHolderr>(diffCallBack)  {
    inner class RepositoryViewHolderr(private val binding: ItemRepositoryInProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(absoluteAdapterPosition)

            binding.apply {
                starCount.text = d.stargazers_count.toString()
                languageName.text = d.language
                repositoryName.text = d.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolderr {
        return RepositoryViewHolderr(
            ItemRepositoryInProfileBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_repository_in_profile, parent, false)
            )
        )
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

    override fun onBindViewHolder(holder: RepositoryViewHolderr, position: Int) {
        holder.bind()
    }


}