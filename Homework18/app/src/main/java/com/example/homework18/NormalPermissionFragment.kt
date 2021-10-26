package com.example.homework18

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class NormalPermissionFragment:Fragment(R.layout.fragment_normal_permission) {
    lateinit var internetImageView: ImageView
    lateinit var loadImage:Button
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        internetImageView = requireView().findViewById(R.id.internetImageView)
        loadImage = requireView().findViewById(R.id.loadImage)
        loadImage.setOnClickListener {
            loadImageFromInternet()
        }


    }


    private fun loadImageFromInternet(){
        Glide.with(this)
            .load("https://likehamster.ru/wp-content/uploads/2018/09/906_result.jpg")
            .into(internetImageView)
    }
}