package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.R
import com.example.github.data.models.GetRepositoriesByNameData
import com.example.github.data.models.GetUserRepositoriesData
import com.example.github.databinding.ItemRepositoryByReponameBinding


class ReposByReposNameAdapter:
ListAdapter<GetRepositoriesByNameData, ReposByReposNameAdapter.RepositoryViewHolder>(diffCallBack)  {
    inner class RepositoryViewHolder(private val binding: ItemRepositoryByReponameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(absoluteAdapterPosition)

            binding.apply {

                akk.text = d.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemRepositoryByReponameBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_search_by_username, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack : DiffUtil.ItemCallback<GetRepositoriesByNameData>() {
        override fun areItemsTheSame(
            oldItem: GetRepositoriesByNameData,
            newItem: GetRepositoriesByNameData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetRepositoriesByNameData,
            newItem: GetRepositoriesByNameData
        ): Boolean {
            return oldItem == newItem
        }

    }
}