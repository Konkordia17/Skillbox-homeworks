package com.example.homework18

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class LocationAdapter(private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<LocationAdapter.Holder>() {
    private var locations: List<Location> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflate(R.layout.item_location), onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val location = locations[position]
        holder.bind(location)
    }

    override fun getItemCount(): Int = locations.size

    fun updateLocations(newLocations: List<Location>) {
        locations = newLocations
    }

    class Holder(
        view: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val idTextView: TextView = view.findViewById(R.id.idTextView)
        private val infoTextView: TextView = view.findViewById(R.id.infoTextView)
        private val image: ImageView = view.findViewById(R.id.image)
        private val timeTextView: TextView = view.findViewById(R.id.timeTextView)

        init {
            view.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        private val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
            .withZone(ZoneId.systemDefault())

        fun bind(location: Location) {
            idTextView.text = location.id.toString()
            infoTextView.text = location.info
            Glide.with(itemView)
                .load(location.image)
                .placeholder(R.drawable.ic_alarms)
                .into(image)
            timeTextView.text = formatter.format(location.time)
        }
    }
}