package com.example.converter.ui.currenciesList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.converter.R
import com.example.converter.data.model.Valute
import com.example.converter.databinding.ItemCurrencyBinding

class CurrencyAdapter(private val onItemClicked: (currency: Valute) -> Unit) :
    RecyclerView.Adapter<CurrencyAdapter.Holder>() {
    private var currencies: List<Valute> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemCurrencyBinding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return Holder(itemCurrencyBinding, onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currency = currencies[position]
        holder.bind(currency)
    }

    override fun getItemCount(): Int = currencies.size

    class Holder(
        private val itemCurrencyBinding: ItemCurrencyBinding,
        val onItemClicked: (currency: Valute) -> Unit
    ) :
        RecyclerView.ViewHolder(itemCurrencyBinding.root) {
        fun bind(currency: Valute) {
            itemCurrencyBinding.nameCurrencyTextView.text = currency.name
            itemCurrencyBinding.charCodeTextView.text = currency.charCode
            Glide.with(itemView)
                .load(R.drawable.ic_check_black)
                .into(itemCurrencyBinding.checkedButton)
            itemView.setOnClickListener {
                onItemClicked(currency)
            }
        }
    }

    fun updateValutes(newList: List<Valute>) {
        currencies = newList
        notifyDataSetChanged()
    }
}

