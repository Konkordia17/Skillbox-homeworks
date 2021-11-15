package com.skillbox.github.ui.repository_list

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.github.R

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.Holder>() {
    private var repositories: List<PublicRepository> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_repository, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository)

    }

    override fun getItemCount(): Int = repositories.size

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageViewRepository: ImageView = view.findViewById(R.id.imageRepository)
        private val nameTextView: TextView = view.findViewById(R.id.nameRepositoryTextView)
        private val fullNameTextView: TextView = view.findViewById(R.id.fullNameRepository)
        fun bind(repository: PublicRepository) {
            nameTextView.text = repository.name
            fullNameTextView.text = repository.full_name
            Glide.with(itemView)
                .load(repository.avatar_url)
                .into(imageViewRepository)
        }
    }

    fun updateRepositories(newRepositories: List<PublicRepository>) {
        repositories = newRepositories
        notifyDataSetChanged()
    }
}