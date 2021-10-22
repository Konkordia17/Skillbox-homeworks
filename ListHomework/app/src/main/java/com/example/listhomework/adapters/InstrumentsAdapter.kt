package com.example.listhomework.adapters

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listhomework.Holder
import com.example.listhomework.MusicalInstruments
import com.example.listhomework.R
import com.example.listhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class InstrumentsAdapter(private val onItemClick: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<MusicalInstruments>(InstrumentsDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(StringInstrumentsAdapterDelegate(onItemClick))
            .addDelegate(WindInstrumentsAdapterDelegate(onItemClick))
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

        override fun areContentsTheSame(
            oldItem: MusicalInstruments,
            newItem: MusicalInstruments
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemCount(): Int = differ.currentList.size
}

