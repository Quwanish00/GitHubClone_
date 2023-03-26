package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.data.models.GetRepositoriesByNameData
import com.example.github.data.models.GetSearchUsersByUsername
import com.example.github.databinding.ItemRepositoryByReponameBinding
import com.example.github.databinding.ItemSearchByUsernameBinding

class RepositoryByUsernameAdapter:
    ListAdapter<GetSearchUsersByUsername, RepositoryByUsernameAdapter.RepositoryViewHolder>(diffCallBack) {
    inner class RepositoryViewHolder(private val binding: ItemSearchByUsernameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(absoluteAdapterPosition)

            binding.apply {
                Glide.with(profil)
                    .load(d.avatar_url)
                    .into(profil)
                akk.text = d.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemSearchByUsernameBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_search_by_username, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack : DiffUtil.ItemCallback<GetSearchUsersByUsername>() {
        override fun areItemsTheSame(
            oldItem: GetSearchUsersByUsername,
            newItem: GetSearchUsersByUsername
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetSearchUsersByUsername,
            newItem: GetSearchUsersByUsername
        ): Boolean {
            return oldItem == newItem
        }

    }
}
