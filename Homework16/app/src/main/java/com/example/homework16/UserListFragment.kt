package com.example.homework16

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework16.adapters.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserListFragment : Fragment(R.layout.fragment_user_list) {
    lateinit var userList: RecyclerView
    lateinit var addFab: FloatingActionButton
    private var users = listOf(
        User(
            name = "Ли Янг",
            avatarLink = "https://cdn.pixabay.com/photo/2021/09/15/11/34/woman-6626615_960_720.jpg",
            age = 25,
            isDeveloper = true
        ),
        User(
            name = "Иван Петров",
            avatarLink = "https://media.istockphoto.com/photos/confident-man-in-blue-sweater-portrait-picture-id536988396",
            age = 33,
            isDeveloper = false
        ),
        User(
            name = "Петр Иванов",
            avatarLink = "https://cdn.pixabay.com/photo/2015/01/08/18/29/entrepreneur-593358_960_720.jpg",
            age = 22,
            isDeveloper = true
        ),
        User(
            name = "Антон Васильев",
            avatarLink = "https://cdn.pixabay.com/photo/2016/01/19/15/05/doctor-1149149_960_720.jpg",
            age = 40,
            isDeveloper = false
        ),
        User(
            name = "Елена Смирнова",
            avatarLink = "https://cdn.pixabay.com/photo/2016/12/19/21/36/woman-1919143_960_720.jpg",
            age = 26,
            isDeveloper = true
        )
    )

    private var userAdapter: UserAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userList = requireView().findViewById(R.id.userList)
        addFab = requireView().findViewById(R.id.addFab)
        initList()
        addFab.setOnClickListener {
            addUser()
        }
        userAdapter?.updateUsers(users)
        userAdapter?.notifyDataSetChanged()
//        userAdapter?.notifyItemRangeInserted(0, users.size)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userAdapter = null
    }

    private fun initList() {
        userAdapter = UserAdapter{position ->deleteUser(position) }
        with(userList) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deleteUser(position: Int) {
users = users.filterIndexed{index, user ->  index!=position}
        userAdapter?.updateUsers(users)
        userAdapter?.notifyItemRemoved(position)

    }

    private fun addUser() {
        val newUser = users.random()
        users = listOf(newUser) + users
        userAdapter?.updateUsers(users)
        userAdapter?.notifyItemInserted(0)
        userList.scrollToPosition(0)
    }

    companion object {
        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): UserListFragment {
            return UserListFragment().withArguments {
                putString(KEY_TEXT, text)
            }

        }
    }
}