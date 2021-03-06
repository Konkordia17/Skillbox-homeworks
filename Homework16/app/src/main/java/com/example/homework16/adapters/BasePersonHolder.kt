package com.example.homework16.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework16.R

abstract class BasePersonHolder(view: View, onItemClick: (id: Long) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    private val ageTextView: TextView = view.findViewById(R.id.ageTextView)
    private val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)

    init {
        view.setOnClickListener {
            onItemClick(itemId)
        }
    }

    protected fun bindMainInfo(
        name: String,
        avatarLink: String,
        age: Int
    ) {
        nameTextView.text = name
        ageTextView.text = "Возраст =${age}"
        Glide.with(itemView)
            .load(avatarLink)
            .placeholder(R.drawable.ic_portrait)
            .into(avatarImageView)
    }
}