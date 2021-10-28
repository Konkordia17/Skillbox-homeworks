package com.example.homework16

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.fragment.navArgs

class DetailsFragment:Fragment(R.layout.fragment_details) {
    lateinit var idTextView: TextView
    private val args:DetailsFragmentArgs by navArgs()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        idTextView = requireView().findViewById(R.id.idTextView)
        idTextView.text = args.personId.toString()

    }

}