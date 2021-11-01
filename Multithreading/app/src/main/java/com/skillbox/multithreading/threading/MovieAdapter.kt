package com.skillbox.multithreading.threading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.multithreading.R
import com.skillbox.multithreading.networking.Movie

class MovieAdapter(private var movie: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = movie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movie.size

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val yearTextView: TextView = view.findViewById(R.id.yearTextView)
        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
        }
    }
}