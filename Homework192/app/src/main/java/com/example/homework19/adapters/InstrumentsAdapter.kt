package com.example.homework19.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.homework19.model.MusicalInstruments
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class InstrumentsAdapter(
    private val onItemClick: (instrument: MusicalInstruments) -> Unit,
    private val onItemLongClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<MusicalInstruments>(InstrumentsDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(
            StringInstrumentsAdapterDelegate(
                onItemClick,
                onItemLongClick
            )
        )
            .addDelegate(WindInstrumentsAdapterDelegate(onItemClick, onItemLongClick))
    }

    class InstrumentsDiffUtilCallback : DiffUtil.ItemCallback<MusicalInstruments>() {
        override fun areItemsTheSame(
            oldItem: MusicalInstruments,
            newItem: MusicalInstruments
        ): Boolean {
            return when {
                oldItem is MusicalInstruments.WindInstruments &&
                        newItem is MusicalInstruments.WindInstruments -> oldItem.id == newItem.id
                oldItem is MusicalInstruments.StringInstruments &&
                        newItem is MusicalInstruments.StringInstruments -> oldItem.id == newItem.id
                else -> false
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: MusicalInstruments,
            newItem: MusicalInstruments
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemCount(): Int = differ.currentList.size
}

