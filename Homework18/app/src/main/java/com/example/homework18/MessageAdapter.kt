package com.example.homework18

import android.view.View
import android.widget.ListAdapter
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter:ListAdapter<Message, MessageAdapter.Holder>(MessageDiffUtilCallback()) {



    }
    class  MessageDiffUtilCallback:DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
           return oldItem==newItem
        }
    }

class Holder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
    fun bind(message: Message){
        messageText.text = message.text
    }

}