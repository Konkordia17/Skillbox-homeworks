package com.example.homework16

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PersonListFragment : Fragment(R.layout.fragment_user_list) {
    lateinit var userList: RecyclerView
    lateinit var addFab: FloatingActionButton
    private var persons = listOf(
        Person.Developer(
            name = "Ли Янг",
            avatarLink = "https://cdn.pixabay.com/photo/2021/09/15/11/34/woman-6626615_960_720.jpg",
            age = 25,
           programmingLanguage = "Java"
        ),
        Person.User(
            name = "Иван Петров",
            avatarLink = "https://media.istockphoto.com/photos/confident-man-in-blue-sweater-portrait-picture-id536988396",
            age = 33,
        ),
        Person.Developer(
            name = "Петр Иванов",
            avatarLink = "https://cdn.pixabay.com/photo/2015/01/08/18/29/entrepreneur-593358_960_720.jpg",
            age = 22,
            programmingLanguage = "Kotlin"
        ),
        Person.User(
            name = "Антон Васильев",
            avatarLink = "https://cdn.pixabay.com/photo/2016/01/19/15/05/doctor-1149149_960_720.jpg",
            age = 40,
        ),
        Person.Developer(
            name = "Елена Смирнова",
            avatarLink = "https://cdn.pixabay.com/photo/2016/12/19/21/36/woman-1919143_960_720.jpg",
            age = 26,
            programmingLanguage = "Java Script"
        )
    )

    private var personAdapter: PersonAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userList = requireView().findViewById(R.id.userList)
        addFab = requireView().findViewById(R.id.addFab)
        initList()
        addFab.setOnClickListener {
            addUser()
        }
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyDataSetChanged()
//        userAdapter?.notifyItemRangeInserted(0, users.size)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        personAdapter = null
    }

    private fun initList() {
        personAdapter = PersonAdapter { position -> deletePerson(position) }
        with(userList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deletePerson(position: Int) {
        persons = persons.filterIndexed { index, user -> index != position }
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyItemRemoved(position)

    }

    private fun addUser() {
        val newUser = persons.random()
        persons = listOf(newUser) + persons
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyItemInserted(0)
        userList.scrollToPosition(0)
    }

    companion object {
        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): PersonListFragment{
            return PersonListFragment().withArguments {
                putString(KEY_TEXT, text)
            }

        }
    }
}