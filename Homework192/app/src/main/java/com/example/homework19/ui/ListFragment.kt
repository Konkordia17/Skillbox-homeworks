package com.example.homework19.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.homework19.*
import com.example.homework19.adapters.InstrumentsAdapter
import com.example.homework19.model.MusicalInstruments
import com.example.homework19.utils.autoCleared
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(R.layout.list_fragment), CreateInstrument {
    private val viewModel: InstrumentListViewModel by viewModels()
    private lateinit var musicalInstrumentsList: RecyclerView
    private lateinit var emptyList: TextView
    private lateinit var addFab: FloatingActionButton

    private var musicalInstrumentsAdapter by autoCleared<InstrumentsAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFab = requireView().findViewById(R.id.addFab)
        musicalInstrumentsList = requireView().findViewById(R.id.musicalInstrumentsList)
        emptyList = requireView().findViewById(R.id.emptyList)
        initList()
        addFab.setOnClickListener {
            showDialog()
        }
        observeViewModelState()
    }

    private fun initList() {
        musicalInstrumentsAdapter = InstrumentsAdapter(
            { instrument->
                val action = ListFragmentDirections
                    .actionListFragmentToDetailsFragment(instrument)
                findNavController().navigate(action)

            }
        ) { position -> deleteInstrument(position) }
        with(musicalInstrumentsList) {
            adapter = musicalInstrumentsAdapter
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
        }
    }

    private fun deleteInstrument(position: Int) {
        viewModel.deleteInstrument(position)
    }

    private fun showDialog() {
        DialogCreateInstruments()
            .show(childFragmentManager, "dialog")
    }

    override fun addInstrument(instruments: MusicalInstruments) {
        viewModel.addInstrument(instruments)
        musicalInstrumentsList.scrollToPosition(0)
    }

    private fun observeViewModelState() {
        viewModel.instruments
            .observe(viewLifecycleOwner) { newList -> musicalInstrumentsAdapter.items = newList }
        viewModel.showToast
            .observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Элемент удален", Toast.LENGTH_SHORT).show()
            }
    }
}