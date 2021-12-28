package com.example.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.database.data.Order
import com.example.database.databinding.ItemOrderBinding

class ProductsBasketAdapter(private val onItemClicked: (order: Order) -> Unit) :
    ListAdapter<Order, ProductsBasketAdapter.ProductsBasketHolder>(ProductDiffUtil()) {
    private val differ = AsyncListDiffer(this, ProductDiffUtil())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsBasketHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        return ProductsBasketHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ProductsBasketHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = differ.currentList.size

    class ProductsBasketHolder(
        private val binding: ItemOrderBinding,
        val onItemClicked: (order: Order) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.productNameTV.text = order.products
            binding.costTV.text = order.cost.toString()
            itemView.setOnClickListener {
                onItemClicked(order)
            }
        }
    }

    fun updateProductsList(newList: List<Order>) {
        differ.submitList(newList)
    }

    class ProductDiffUtil : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return newItem == oldItem
        }
    }
}