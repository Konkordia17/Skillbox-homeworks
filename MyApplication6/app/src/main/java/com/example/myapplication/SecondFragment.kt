package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAGF", "SecondFragment onCreateView")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAGF", "SecondFragment onStart")
    }

    override fun onPause() {
        Log.d("TAGF", "SecondFragment onPause")
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAGF", "SecondFragment onViewCreated")
    }

    override fun onStop() {
        Log.d("TAGF", "SecondFragment onStop")
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAGF", "SecondFragment onCreate")
    }
}