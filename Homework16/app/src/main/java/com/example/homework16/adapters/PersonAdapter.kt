package com.example.homework16.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework16.Person
import com.example.homework16.R
import com.example.homework16.inflate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PersonAdapter(
    private val onItemClick: (position: Int) -> Unit
//) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
):AsyncListDifferDelegationAdapter<Person>(PersonDifUtilCallback()){

//    private val differ = AsyncListDiffer<Person>(this, PersonDifUtilCallback())
//    private val delegatesManager = AdapterDelegatesManager<List<Person>>()

    init {
        delegatesManager.addDelegate(UserAdapterDelegate(onItemClick))
            .addDelegate(DeveloperAdapterDelegate(onItemClick))
    }

//    private var persons: List<Person> = emptyList()


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
////        return when(viewType){
////            TYPE_USER ->,
////            TYPE_DEVELOPER ->,
////            else -> error("Incorrect viewType=$viewType")
////        }
//        return delegatesManager.onCreateViewHolder(parent, viewType)
//
//    }

//    override fun getItemViewType(position: Int): Int {
////        return when (differ.currentList[position]) {
////            is Person.Developer -> TYPE_DEVELOPER
////            is Person.User -> TYPE_USER
////        }
//        return delegatesManager.getItemViewType(differ.currentList, position)
//    }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
////        when(holder){
////            is UserHolder -> {
////                val person = differ.currentList[position].let { it as? Person.User }
////                    ?: error("Person at position = $position is not a user")
////                holder.bind(person)
////            }
////            is DeveloperHolder ->{
////                val person = differ.currentList[position].let { it as? Person.Developer }
////                    ?: error("Person at position = $position is not a developer")
////                holder.bind(person)
////            }
////            else -> error("Incorrect view holder = $holder")
////        }
//        delegatesManager.onBindViewHolder(differ.currentList, position, holder)
//    }

//    override fun getItemCount(): Int = differ.currentList.size

//    fun updatePersons(newPersons: List<Person>) {
//        differ.submitList(newPersons)
////        persons = newPersons
//    }

    class PersonDifUtilCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return when {
                oldItem is Person.Developer && newItem is Person.Developer -> oldItem.id == newItem.id
                oldItem is Person.User && newItem is Person.User -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

//    class DeveloperHolder(
//        view: View, onItemClick: (position: Int) -> Unit
//    ) : BasePersonHolder(view, onItemClick) {
//        private val programmingLanguageView: TextView =
//            view.findViewById(R.id.programmingLanguageTextView)
//
//        fun bind(person: Person.Developer) {
//            bindMainInfo(person.name, person.avatarLink, person.age)
//            programmingLanguageView.text = "Язык программирования ${person.programmingLanguage}"
//        }
//    }

//    companion object {
//        private const val TYPE_USER = 1
//        private const val TYPE_DEVELOPER = 2
//    }
//

}
