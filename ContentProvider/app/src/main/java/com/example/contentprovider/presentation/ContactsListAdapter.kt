package com.example.contentprovider.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovider.data.Contact
import com.example.contentprovider.databinding.ItemContactBinding

class ContactsListAdapter(private val onItemClicked: (contact: Contact) -> Unit) :
    RecyclerView.Adapter<ContactsListAdapter.Holder>() {
    private var contactsList: List<Contact> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemContact = ItemContactBinding.inflate(inflater, parent, false)
        return Holder(itemContact, onItemClicked)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val contact = contactsList[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = contactsList.size

    class Holder(
        private val itemContactBinding: ItemContactBinding,
        val onItemClicked: (contact: Contact) -> Unit
    ) :
        RecyclerView.ViewHolder(itemContactBinding.root) {
        fun bind(contact: Contact) {
            itemContactBinding.nameTextView.text = contact.name
            itemView.setOnClickListener {
                onItemClicked(contact)
            }
        }
    }

    fun updateContactsList(newList: List<Contact>) {
        contactsList = newList
        notifyDataSetChanged()
    }
}