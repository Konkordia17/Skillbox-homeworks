package com.example.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.database.data.Order
import com.example.database.databinding.ItemOrderBinding

class OrderAdapter(private val onItemClicked: (order: Order) -> Unit) :
    RecyclerView.Adapter<OrderAdapter.Holder>() {
    private var orders = emptyList<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        return Holder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int = orders.size

    class Holder(private val binding: ItemOrderBinding, val onItemClicked: (order: Order) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.productNameTV.text = order.products
            binding.costTV.text = order.cost.toString()
            itemView.setOnClickListener {
                onItemClicked(order)
            }
        }
    }

    fun updateList(newList: List<Order>) {
        orders = newList
    }
}