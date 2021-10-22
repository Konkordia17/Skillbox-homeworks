
package com.example.listhomework

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

abstract class Holder(view: View, onItemClick: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val stringCount: TextView = view.findViewById(R.id.stringCountTextView)
    private val imageView: ImageView = view.findViewById(R.id.imageView)

    init {
        view.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    protected fun bindMainInfo(
        name: String,
        image: String,

        ) {
        nameTextView.text = name
        Glide.with(itemView)
            .load(image)
            .placeholder(R.drawable.ic_crop)
            .into(imageView)
    }
}
