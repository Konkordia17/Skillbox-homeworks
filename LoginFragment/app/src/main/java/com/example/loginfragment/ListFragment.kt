package com.example.loginfragment

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.activity_list) {
    private val navigator: Navigator?
        get() = parentFragment?.let { it as? Navigator }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? Button }
            .forEach { button ->
                button.setOnClickListener {
                    navigator?.navigateTo(DetailFragment())
                }
            }
    }
}
