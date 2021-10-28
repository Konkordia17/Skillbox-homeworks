package com.example.homework16.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.homework16.Person
import com.example.homework16.R
import com.example.homework16.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class DeveloperAdapterDelegate(private val onItemClick: (id:Long) -> Unit):
    AbsListItemAdapterDelegate<Person.Developer, Person, DeveloperAdapterDelegate.DeveloperHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Developer
    }

    override fun onCreateViewHolder(parent: ViewGroup): DeveloperHolder {
        return DeveloperHolder(parent.inflate(R.layout.item_developer), onItemClick)
    }

    override fun onBindViewHolder(
        item: Person.Developer,
        holder:DeveloperHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
    class DeveloperHolder(
        view: View, onItemClick: (id:Long) -> Unit
    ) : BasePersonHolder(view, onItemClick){
        private val programmingLanguageView:TextView = view.findViewById(R.id.programmingLanguageTextView)

        fun bind(person: Person.Developer){
            bindMainInfo(person.name, person.avatarLink, person.age)
            programmingLanguageView.text = "Язык программирования ${person.programmingLanguage}"
        }
    }
}