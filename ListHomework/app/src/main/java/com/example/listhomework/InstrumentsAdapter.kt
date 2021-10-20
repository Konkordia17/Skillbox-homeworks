package com.example.listhomework

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class InstrumentsAdapter(private val onItemClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var musicalInstruments: List<MusicalInstruments> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            TYPE_STRING -> StringInstrumentsHolder(
                parent.inflate(R.layout.item_string_instruments), onItemClick
            )
            TYPE_WIND
            -> WindInstrumentHolder(parent.inflate(R.layout.item_wind_instruments), onItemClick)
            else -> error("Incorrect viewType=$viewType")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (musicalInstruments[position]) {
            is MusicalInstruments.StringInstruments -> TYPE_STRING
            is MusicalInstruments.WindInstruments -> TYPE_WIND
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StringInstrumentsHolder -> {
                val musicalInstruments =
                    musicalInstruments[position].let { it as? MusicalInstruments.StringInstruments }
                        ?: error("Musical Instruments at position = $position is not a string instrument")
                holder.bind(musicalInstruments)
            }
            is WindInstrumentHolder -> {
                val musicalInstruments =
                    musicalInstruments[position].let { it as? MusicalInstruments.WindInstruments }
                        ?: error("Musical Instruments at position = $position is not a wind instrument")
                holder.bind(musicalInstruments)
            }
            else -> error("Incorrect view holder = $holder")
        }
    }

    fun updateInstruments(newInstruments: List<MusicalInstruments>) {
        musicalInstruments = newInstruments
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = musicalInstruments.size

    abstract class Holder(view: View, onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val stringCount: TextView = view.findViewById(R.id.stringCountTextView)
        private val imageView: ImageView = view.findViewById(R.id.imageView)

        init {
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        protected fun bindMainInfo(
            name: String,
            image: String,

            ) {
            nameTextView.text = name
            Glide.with(itemView)
                .load(image)
                .placeholder(R.drawable.ic_crop)
                .into(imageView)
        }
    }

    class StringInstrumentsHolder(
        view: View, onItemClick: (position: Int) -> Unit
    ) : Holder(view, onItemClick) {
        @SuppressLint("SetTextI18n")
        fun bind(musicalInstruments: MusicalInstruments.StringInstruments) {
            bindMainInfo(musicalInstruments.name, musicalInstruments.image)
            stringCount.text = "Количество струн ${musicalInstruments.stringCount}"
        }
    }

    class WindInstrumentHolder(
        view: View, onItemClick: (position: Int) -> Unit
    ) : Holder(view, onItemClick) {
        fun bind(musicalInstruments: MusicalInstruments.WindInstruments) {
            bindMainInfo(musicalInstruments.name, musicalInstruments.image)
        }
    }

    companion object {
        private const val TYPE_STRING = 1
        private const val TYPE_WIND = 2
    }
}

