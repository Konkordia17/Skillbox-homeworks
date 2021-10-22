package com.example.listhomework.adapters

import android.view.View
import android.view.ViewGroup
import com.example.listhomework.Holder
import com.example.listhomework.MusicalInstruments
import com.example.listhomework.R
import com.example.listhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class WindInstrumentsAdapterDelegate (private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<MusicalInstruments.WindInstruments, MusicalInstruments, WindInstrumentsAdapterDelegate.WindInstrumentHolder>() {
    override fun isForViewType(
        item: MusicalInstruments,
        items: MutableList<MusicalInstruments>,
        position: Int
    ): Boolean {
        return item is MusicalInstruments.WindInstruments
    }

    override fun onCreateViewHolder(parent: ViewGroup): WindInstrumentHolder {
      return WindInstrumentHolder(parent.inflate(R.layout.item_wind_instruments), onItemClick)
    }

    override fun onBindViewHolder(
        item: MusicalInstruments.WindInstruments,
        holder: WindInstrumentHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
    class WindInstrumentHolder(
        view: View, onItemClick: (position: Int) -> Unit
    ) : Holder(view, onItemClick) {
        fun bind(musicalInstruments: MusicalInstruments.WindInstruments) {
            bindMainInfo(musicalInstruments.name, musicalInstruments.image)
        }
    }
}


