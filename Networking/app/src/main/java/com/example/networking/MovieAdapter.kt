package com.example.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networking.databinding.ItemMovieBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.Holder>(MovieDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return Holder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.idIMDB == newItem.idIMDB
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(private val itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun bind(movie: Movie) {
            itemMovieBinding.titleTextView.text = movie.title
            itemMovieBinding.typeTextView.text = movie.type
            itemMovieBinding.yearTextView.text = movie.year
            itemMovieBinding.idImbdTextView.text = movie.idIMDB

            Glide.with(itemView)
                .load(movie.poster)
                .placeholder(R.drawable.ic_aspect_)
                .into(itemMovieBinding.posterImageView)
        }
    }
}