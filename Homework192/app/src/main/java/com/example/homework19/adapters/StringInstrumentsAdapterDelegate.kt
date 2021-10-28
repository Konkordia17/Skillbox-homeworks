package com.example.homework19.adapters

import android.view.View
import android.view.ViewGroup
import com.example.homework19.R
import com.example.homework19.model.MusicalInstruments
import com.example.homework19.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class StringInstrumentsAdapterDelegate(
    private val onItemClick: (instrument: MusicalInstruments) -> Unit,
    private val onItemLongClick: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<MusicalInstruments.StringInstruments,
            MusicalInstruments, StringInstrumentsAdapterDelegate.StringInstrumentsHolder>()
{
    override fun isForViewType(
        item: MusicalInstruments,
        items: MutableList<MusicalInstruments>,

        position: Int
    ): Boolean {
        return item is MusicalInstruments.StringInstruments
    }

    override fun onCreateViewHolder(parent: ViewGroup): StringInstrumentsHolder {
        return StringInstrumentsHolder(
            parent.inflate(R.layout.item_string_instruments), onItemLongClick
        )
    }

    override fun onBindViewHolder(
        item: MusicalInstruments.StringInstruments,
        holder: StringInstrumentsHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }


    class StringInstrumentsHolder(
        view: View, onItemLongClick: (position: Int) -> Unit
    ) : Holder(view, onItemLongClick) {
        fun bind(musicalInstruments: MusicalInstruments.StringInstruments) {
            bindMainInfo(musicalInstruments.name, musicalInstruments.image)
            stringCount.text = "Количество струн ${musicalInstruments.stringCount}"
        }
    }

}