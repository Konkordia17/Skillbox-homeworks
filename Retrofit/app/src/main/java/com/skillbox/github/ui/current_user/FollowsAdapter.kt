package com.skillbox.github.ui.current_user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.github.databinding.ItemRepositoryBinding

class FollowsAdapter :
    RecyclerView.Adapter<FollowsAdapter.FollowHolder>() {
    private var followersList: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemFollowsBinding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return FollowHolder(itemFollowsBinding)
    }

    override fun onBindViewHolder(holder: FollowHolder, position: Int) {
        val follow = followersList[position]
        holder.bind(follow)
    }

    override fun getItemCount(): Int = followersList.size

    class FollowHolder(
        private val itemFollowsBinding: ItemRepositoryBinding,
    ) : RecyclerView.ViewHolder(itemFollowsBinding.root) {
        fun bind(follow: User) {
            itemFollowsBinding.nameRepositoryTextView.text = follow.login
            Glide.with(itemView)
                .load(follow.avatar_url)
                .into(itemFollowsBinding.imageRepository)
        }
    }

    fun updateFollowersList(newFollowersList: List<User>) {
        followersList = newFollowersList
        notifyDataSetChanged()
    }
}