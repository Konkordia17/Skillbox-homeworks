package com.example.listhomework

import android.os.Bundle
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(R.layout.list_fragment), CreateInstrument {
    private lateinit var musicalInstrumentsList: RecyclerView
    lateinit var emptyList: TextView
    lateinit var addFab: FloatingActionButton
    private var musicalInstruments = listOf(
        MusicalInstruments.StringInstruments(
            name = "Скрипка",
            image = "https://cdn.pixabay.com/photo/2016/09/02/10/01/violin-1638742_960_720.png",
            stringCount = "4"
        ),
        MusicalInstruments.WindInstruments(
            name = "Труба",
            image = "https://cdn.pixabay.com/photo/2020/03/31/13/33/music-4987649_960_720.jpg",
        ),
        MusicalInstruments.StringInstruments(
            name = "Арфа",
            image = "https://cdn.pixabay.com/photo/2019/11/04/11/48/harp-4600984_960_720.png",
            stringCount = "19"
        ),
        MusicalInstruments.WindInstruments(
            name = "Гобой",
            image = "https://cdn.pixabay.com/photo/2012/04/13/00/39/oboe-31356_960_720.png",
        ),
        MusicalInstruments.StringInstruments(
            name = "Балалайка",
            image = "https://cdn.pixabay.com/photo/2013/07/13/10/06/balalaika-156560_960_720.png",
            stringCount = "3"
        ),
        MusicalInstruments.WindInstruments(
            name = "Саксофон",
            image = "https://cdn.pixabay.com/photo/2012/04/12/12/30/saxophone-29816_960_720.png",
        ),
    )
    private var musicalInstrumentsAdapter by autoCleared<InstrumentsAdapter>()
//    private var musicalInstrumentsAdapter: InstrumentsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addFab = requireView().findViewById(R.id.addFab)
        musicalInstrumentsList = requireView().findViewById(R.id.musicalInstrumentsList)
        emptyList = requireView().findViewById(R.id.emptyList)
        initList()
        addFab.setOnClickListener {
            showDialog()
        }
        musicalInstrumentsAdapter.updateInstruments(musicalInstruments)
        musicalInstrumentsAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        musicalInstrumentsAdapter = null
    }

    private fun initList() {
        musicalInstrumentsAdapter = InstrumentsAdapter { position -> deleteInstrument(position) }
        with(musicalInstrumentsList) {
            adapter = musicalInstrumentsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteInstrument(position: Int) {
        musicalInstruments = musicalInstruments.filterIndexed { index, _ -> index != position }
        musicalInstrumentsAdapter.updateInstruments(musicalInstruments)
        musicalInstrumentsAdapter.notifyItemRemoved(position)
        if (musicalInstruments.isEmpty()) {
            emptyList.isVisible = true
        }
    }

    private fun showDialog() {
        DialogCreateInstruments()
            .show(childFragmentManager, "dialog")
    }

    override fun addInstrument(instruments: MusicalInstruments) {
        musicalInstruments = listOf(instruments) + musicalInstruments
        musicalInstrumentsAdapter.updateInstruments(musicalInstruments)
        musicalInstrumentsAdapter.notifyItemInserted(0)
        musicalInstrumentsList.scrollToPosition(0)
        if (musicalInstruments.isNotEmpty()) {
            emptyList.isVisible = false
        }
    }
}