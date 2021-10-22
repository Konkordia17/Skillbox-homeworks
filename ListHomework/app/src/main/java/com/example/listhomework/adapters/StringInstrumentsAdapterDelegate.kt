package com.example.listhomework.adapters

import android.view.View
import android.view.ViewGroup
import com.example.listhomework.Holder
import com.example.listhomework.MusicalInstruments
import com.example.listhomework.R
import com.example.listhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class StringInstrumentsAdapterDelegate(private val onItemClick: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<MusicalInstruments.StringInstruments, MusicalInstruments, StringInstrumentsAdapterDelegate.StringInstrumentsHolder>() {
    override fun isForViewType(
        item: MusicalInstruments,
        items: MutableList<MusicalInstruments>,

        position: Int
    ): Boolean {
        return item is MusicalInstruments.StringInstruments
    }

    override fun onCreateViewHolder(parent: ViewGroup): StringInstrumentsHolder {
        return StringInstrumentsHolder(
            parent.inflate(R.layout.item_string_instruments), onItemClick)
    }

    override fun onBindViewHolder(
        item: MusicalInstruments.StringInstruments,
        holder: StringInstrumentsHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class StringInstrumentsHolder(
        view: View, onItemClick: (position: Int) -> Unit
    ) : Holder(view, onItemClick) {
        fun bind(musicalInstruments: MusicalInstruments.StringInstruments) {
            bindMainInfo(musicalInstruments.name, musicalInstruments.image)
            stringCount.text = "Количество струн ${musicalInstruments.stringCount}"
        }
    }

}