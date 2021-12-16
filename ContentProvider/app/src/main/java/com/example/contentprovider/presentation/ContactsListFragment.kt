package com.example.contentprovider.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contentprovider.R
import com.example.contentprovider.databinding.FragmentContactsListBinding

class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {
    private lateinit var binding: FragmentContactsListBinding
    private var contactsListAdapter: ContactsListAdapter? = null
    private val viewModel: ContactsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeViewModels()
        checkPermission(Manifest.permission.READ_CONTACTS, PERMISSION_CODE)
        binding.addContactButton.setOnClickListener {
            findNavController().navigate(R.id.action_contactsListFragment_to_addContactFragment)
        }

    }

    private fun observeViewModels() {
        viewModel.contacts.observe(viewLifecycleOwner) {
            contactsListAdapter?.updateContactsList(it)

        }
    }

    private fun checkPermission(permission: String, code: Int) {
        val isPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), permission
        ) == PackageManager.PERMISSION_GRANTED
        if (isPermissionGranted) {
            viewModel.getList()
        } else {
            requestPermission(permission, code)
        }
    }

    private fun requestPermission(permission: String, code: Int) {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(permission), code
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            if (requestCode == PERMISSION_CODE) {
                viewModel.getList()
            } else {
                Toast.makeText(context, "Измените настройки", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initList() {
        contactsListAdapter = ContactsListAdapter { contact ->
            val action =
                ContactsListFragmentDirections.actionContactsListFragmentToDetailInfoFragment(
                    contact
                )
            findNavController().navigate(action)
        }
        with(binding.contactsList) {
            adapter = contactsListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    companion object {
        const val PERMISSION_CODE = 5
    }

}