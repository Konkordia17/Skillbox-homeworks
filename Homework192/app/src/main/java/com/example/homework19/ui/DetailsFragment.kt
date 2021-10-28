package com.example.homework19.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework19.R

class DetailsFragment : Fragment(R.layout.fragment_details) {
    lateinit var imageView: ImageView
    lateinit var nameTextView: TextView
    private val args: DetailsFragmentArgs by navArgs()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val instrument = args.infoInstrument
        imageView = requireView().findViewById(R.id.imageView)
        nameTextView = requireView().findViewById(R.id.nameTextView)
        Glide.with(requireView())
            .load(instrument.image)
            .placeholder(R.drawable.ic_crop)
            .into(imageView)
        nameTextView.text = instrument.name
    }
}