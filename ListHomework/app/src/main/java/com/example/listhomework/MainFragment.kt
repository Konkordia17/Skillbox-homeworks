package com.example.listhomework

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainFragment : Fragment(R.layout.main_fragment) {
    private val navigator: Navigator?
        get() {
            return activity?.let { it as? Navigator }
        }
    lateinit var linearVertical: Button
    lateinit var linearHorizontal: Button
    lateinit var grid: Button
    lateinit var staggeredGrid: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearVertical = requireView().findViewById(R.id.linearVertical)
        linearHorizontal = requireView().findViewById(R.id.linear_horizontal)
        grid = requireView().findViewById(R.id.grid)
        staggeredGrid = requireView().findViewById(R.id.staggeredGrid)


        linearVertical.setOnClickListener {
            navigator?.navigateTo(ListFragment.newInstance(LayoutManager.LINEAR_VERTICAL))
        }
        linearHorizontal.setOnClickListener {
            navigator?.navigateTo(ListFragment.newInstance(LayoutManager.LINEAR_HORIZONTAL))
        }
        grid.setOnClickListener {
            navigator?.navigateTo(ListFragment.newInstance(LayoutManager.GRID))
        }
        staggeredGrid.setOnClickListener {
            navigator?.navigateTo(ListFragment.newInstance(LayoutManager.STAGGERED_GRID))
        }
    }

}