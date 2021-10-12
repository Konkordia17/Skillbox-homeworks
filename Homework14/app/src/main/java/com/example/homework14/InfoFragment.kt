package com.example.homework14

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class InfoFragment : Fragment(R.layout.fragment_info){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val textView = requireView().findViewById<TextView>(R.id.inputTextView)
        textView.text = requireArguments().getString(KEY_TEXT)
    }



    companion object{
        private const val KEY_TEXT = "key_text"

        fun newInstance(text:String): InfoFragment {
            return InfoFragment().withArguments {
                putString(KEY_TEXT,text)
            }

        }
    }
}