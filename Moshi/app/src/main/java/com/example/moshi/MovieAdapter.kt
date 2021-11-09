package com.example.moshi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moshi.databinding.ItemMovieBinding


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
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(private val itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            itemMovieBinding.titleTextView.text = "Фильм: ${movie.title}"
            itemMovieBinding.yearTextView.text = "Год: ${movie.year}"
            itemMovieBinding.ratingTextView.text = "Рейтинг: ${movie.rating}"
            itemMovieBinding.genreTextView.text = "Жанр: ${movie.genre}"
            itemMovieBinding.idImbdTextView.text = "Id: ${movie.imdbID}"
            itemMovieBinding.scoreTextView.text = "Оценки: ${movie.score}"

            Glide.with(itemView)
                .load(movie.poster)
                .placeholder(R.drawable.ic_aspect_)
                .into(itemMovieBinding.posterImageView)
        }
    }
}
