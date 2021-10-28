package com.example.listhomework

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.listhomework.adapters.InstrumentsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jp.wasabeef.recyclerview.animators.ScaleInAnimator

class ListFragment : Fragment(R.layout.list_fragment), CreateInstrument {
    private val navigator: Navigator?
        get() {
            return activity?.let { it as? Navigator }
        }
    private lateinit var musicalInstrumentsList: RecyclerView
    lateinit var emptyList: TextView
    lateinit var returnButton: ImageButton
    lateinit var addFab: FloatingActionButton

    private var musicalInstruments = listOf(
        MusicalInstruments.StringInstruments(
            id = 1,
            name = "Скрипка",
            image = "https://cdn.pixabay.com/photo/2016/09/02/10/01/violin-1638742_960_720.png",
            stringCount = "4"
        ),
        MusicalInstruments.WindInstruments(
            id = 2,
            name = "Труба",
            image = "https://cdn.pixabay.com/photo/2020/03/31/13/33/music-4987649_960_720.jpg",
        ),
        MusicalInstruments.StringInstruments(
            id = 3,
            name = "Арфа",
            image = "https://cdn.pixabay.com/photo/2019/11/04/11/48/harp-4600984_960_720.png",
            stringCount = "19"
        ),
        MusicalInstruments.WindInstruments(
            id = 4,
            name = "Гобой",
            image = "https://cdn.pixabay.com/photo/2012/04/13/00/39/oboe-31356_960_720.png",
        ),
        MusicalInstruments.StringInstruments(
            id = 5,
            name = "Балалайка",
            image = "https://cdn.pixabay.com/photo/2013/07/13/10/06/balalaika-156560_960_720.png",
            stringCount = "3"
        ),
        MusicalInstruments.WindInstruments(
            id = 6,
            name = "Саксофон",
            image = "https://cdn.pixabay.com/photo/2012/04/12/12/30/saxophone-29816_960_720.png",
        ),
    )

    private var musicalInstrumentsAdapter by autoCleared<InstrumentsAdapter>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addFab = requireView().findViewById(R.id.addFab)
        musicalInstrumentsList = requireView().findViewById(R.id.musicalInstrumentsList)
        returnButton = requireView().findViewById(R.id.returnButton)
        emptyList = requireView().findViewById(R.id.emptyList)
        returnButton.setOnClickListener {
            navigator?.navigateTo(MainFragment())
        }


        val type = requireArguments().getParcelable<LayoutManager>(KEY)

        initList(type ?: LayoutManager.LINEAR_VERTICAL)
        addFab.setOnClickListener {
            showDialog()
        }
        musicalInstrumentsAdapter.items = musicalInstruments
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initList(type: LayoutManager) {
        musicalInstrumentsAdapter = InstrumentsAdapter { position -> deleteInstrument(position) }
        with(musicalInstrumentsList) {
            adapter = musicalInstrumentsAdapter
            setHasFixedSize(true)
            when (type) {
                LayoutManager.LINEAR_VERTICAL -> {
                    layoutManager =
                        LinearLayoutManager(requireContext()).apply {
                            orientation = LinearLayoutManager.VERTICAL
                        }
                    val scrollListener = object :
                        EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
                        override fun onLoadMore(
                            page: Int,
                            totalItemsCount: Int,
                            view: RecyclerView?
                        ) {
                            loadNextDataFromApi(5)
                        }
                    }
                    musicalInstrumentsList.addOnScrollListener(scrollListener)
                }
                LayoutManager.LINEAR_HORIZONTAL -> layoutManager =
                    LinearLayoutManager(requireContext()).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }
                LayoutManager.GRID -> layoutManager = GridLayoutManager(context, 3)
                LayoutManager.STAGGERED_GRID -> layoutManager =
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
            itemAnimator = ScaleInAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            addItemDecoration(ItemOffsetDecoration(requireContext()))

        }
    }

    private fun loadNextDataFromApi(offset: Int) {

    }

    private fun deleteInstrument(position: Int) {
        musicalInstruments = musicalInstruments.filterIndexed { index, _ -> index != position }
        musicalInstrumentsAdapter.items = musicalInstruments
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
        musicalInstrumentsAdapter.items = musicalInstruments
        musicalInstrumentsList.scrollToPosition(0)
        if (musicalInstruments.isNotEmpty()) {
            emptyList.isVisible = false
        }
    }

    companion object {
        private const val KEY = "Key"
        fun newInstance(layout: LayoutManager): ListFragment {
            val fragment = ListFragment()
            val args = Bundle().apply {
                putParcelable(KEY, layout)
            }
            fragment.arguments = args
            return fragment
        }
    }
}