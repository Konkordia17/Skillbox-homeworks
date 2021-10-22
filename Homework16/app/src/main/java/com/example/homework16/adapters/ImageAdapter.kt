package com.example.homework16.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework16.R
import com.example.homework16.inflate

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var images: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            parent.inflate(
                R.layout.item_image
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    fun setImages(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val imageView: ImageView = containerView.findViewById(R.id.imageView)
        fun bind(imageUrl: String) {
            Glide.with(itemView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_portrait)
                .into(imageView)
        }
    }
}
