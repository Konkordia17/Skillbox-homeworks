package com.example.homework16

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework16.adapters.PersonAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import kotlin.random.Random

class PersonListFragment : Fragment(R.layout.fragment_user_list) {
    lateinit var userList: RecyclerView
    lateinit var addFab: FloatingActionButton

    private var personAdapter: PersonAdapter? = null
    private val personListViewModel: PersonListViewModel by viewModels()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userList = requireView().findViewById(R.id.userList)
        addFab = requireView().findViewById(R.id.addFab)
        initList()
        addFab.setOnClickListener {
            addUser()
        }
//        updatePersonList()
        observeViewModelState()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        personAdapter = null
    }

    private fun initList() {
        personAdapter = PersonAdapter { id ->
         val action=  PersonListFragmentDirections.actionPersonListFragmentToDetailsFragment(id)
            findNavController().navigate(action)
            findNavController().popBackStack()
        }
        with(userList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
    }

    private fun deletePerson(position: Int) {
        personListViewModel.deletePerson(position)
//       updatePersonList()

    }

    private fun addUser() {
        personListViewModel.addPerson()
//       updatePersonList()
        userList.scrollToPosition(0)
    }

    //    private fun updatePersonList() {
//        personAdapter?.items = personListViewModel.getPersonList()
//    }
    private fun observeViewModelState() {
        personListViewModel.persons
            .observe(viewLifecycleOwner) { newPersons -> personAdapter?.items = newPersons }
        personListViewModel.showToast
            .observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Элемент добавлен", Toast.LENGTH_SHORT).show()
            }

    }

}