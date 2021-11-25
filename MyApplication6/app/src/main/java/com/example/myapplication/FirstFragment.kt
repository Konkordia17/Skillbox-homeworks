package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAGF", "FirstFragment onCreateView")

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAGF", "FirstFragment onStart")
    }

    override fun onPause() {
        Log.d("TAGF", "FirstFragment onPause")
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstButton.setOnClickListener {
            Toast.makeText(requireContext(), "кнопка", Toast.LENGTH_SHORT).show()
        }
        Log.d("TAGF", "FirstFragment onViewCreated")
    }

    override fun onStop() {
        Log.d("TAGF", "FirstFragment onStop")
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAGF", "FirstFragment onCreate")
    }

}