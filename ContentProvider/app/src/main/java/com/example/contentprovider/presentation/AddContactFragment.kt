package com.example.contentprovider.presentation

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
import com.example.contentprovider.R
import com.example.contentprovider.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment(R.layout.fragment_add_contact) {
    private lateinit var binding: FragmentAddContactBinding
    private val viewModel: AddContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNewContactButton.setOnClickListener {
            if (binding.addNameEditText.text.isBlank() ||
                binding.addNumberEditText.text.isBlank()
            ) {
                Toast.makeText(
                    context,
                    "Не заполнены поля \"Имя\" или \"Номер телефона\"",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.save(
                    name = binding.addNameEditText.text.toString(),
                    phone = binding.addNumberEditText.text.toString(),
                    email = binding.addEmailEditText.text.toString()
                )
                findNavController().navigate(R.id.action_addContactFragment_to_contactsListFragment)
            }
        }
        checkPermission(
            android.Manifest.permission.WRITE_CONTACTS,
            ContactsListFragment.PERMISSION_CODE
        )
    }

    private fun checkPermission(permission: String, code: Int) {
        val isPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), permission
        ) == PackageManager.PERMISSION_GRANTED
        if (isPermissionGranted) {
            viewModel.save(
                name = binding.addNameEditText.text.toString(),
                phone = binding.addNumberEditText.text.toString(),
                email = binding.addEmailEditText.text.toString()
            )
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
            if (requestCode == ContactsListFragment.PERMISSION_CODE) {
                viewModel.save(
                    name = binding.addNameEditText.text.toString(),
                    phone = binding.addNumberEditText.text.toString(),
                    email = binding.addEmailEditText.text.toString()
                )
            } else {
                Toast.makeText(context, "Измените настройки", Toast.LENGTH_SHORT).show()
            }
        }
    }
}