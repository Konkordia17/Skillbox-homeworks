package com.example.homework14

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.homework14.R

class ListFragment : Fragment(R.layout.fragment_list) {
    private val itemSelectListener:ItemSelectListener?
    get() = activity?.let { it as? ItemSelectListener }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    onButtonClick(button.text.toString())
                }
            }
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun onButtonClick(buttonText: String) {
        itemSelectListener?.onItemSelected(buttonText)
//        (activity as? ItemSelectListener).onItemSelected(buttonText)
    }}
