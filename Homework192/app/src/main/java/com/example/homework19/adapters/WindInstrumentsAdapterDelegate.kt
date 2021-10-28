package com.example.homework19.adapters

import android.view.View
import android.view.ViewGroup
import com.example.homework19.R
import com.example.homework19.model.MusicalInstruments
import com.example.homework19.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class WindInstrumentsAdapterDelegate(
    private val onItemClick: (instrument: MusicalInstruments) -> Unit,
    private val onItemLongClick: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<MusicalInstruments.WindInstruments,
            MusicalInstruments, WindInstrumentsAdapterDelegate.WindInstrumentHolder>() {
    override fun isForViewType(
        item: MusicalInstruments,
        items: MutableList<MusicalInstruments>,
        position: Int
    ): Boolean {
        return item is MusicalInstruments.WindInstruments
    }

    override fun onCreateViewHolder(parent: ViewGroup): WindInstrumentHolder {
        return WindInstrumentHolder(
            parent.inflate(R.layout.item_wind_instruments),
            onItemLongClick
        )
    }

    override fun onBindViewHolder(
        item: MusicalInstruments.WindInstruments,
        holder: WindInstrumentHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    class WindInstrumentHolder(
        view: View, onItemLongClick: (position: Int) -> Unit
    ) : Holder(view, onItemLongClick) {
        fun bind(musicalInstruments: MusicalInstruments.WindInstruments) {
            bindMainInfo(musicalInstruments.name, musicalInstruments.image)
        }
    }
}


