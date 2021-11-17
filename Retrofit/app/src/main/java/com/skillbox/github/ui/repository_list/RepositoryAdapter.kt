package com.skillbox.github.ui.repository_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.github.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val onItemClicked: (repository: PublicRepository) -> Unit) :
    RecyclerView.Adapter<RepositoryAdapter.Holder>() {
    private var repositories: List<PublicRepository> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRepositoryBinding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return Holder(itemRepositoryBinding, onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository)
    }

    override fun getItemCount(): Int = repositories.size

    class Holder(
        private val itemRepositoryBinding: ItemRepositoryBinding,
        val onItemClicked: (repository: PublicRepository) -> Unit
    ) : RecyclerView.ViewHolder(itemRepositoryBinding.root) {

        fun bind(repository: PublicRepository) {
            itemRepositoryBinding.nameRepositoryTextView.text = repository.name
            itemRepositoryBinding.fullNameRepository.text = repository.full_name
            Glide.with(itemView)
                .load(repository.owner.avatar_url)
                .into(itemRepositoryBinding.imageRepository)
            itemView.setOnClickListener {
                onItemClicked(repository)
            }
        }
    }

    fun updateRepositories(newRepositories: List<PublicRepository>) {
        repositories = newRepositories
        notifyDataSetChanged()
    }
}