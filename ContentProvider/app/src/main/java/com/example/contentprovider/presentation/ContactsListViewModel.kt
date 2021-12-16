package com.example.contentprovider.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contentprovider.data.Contact
import com.example.contentprovider.data.ContactsRepository
import kotlinx.coroutines.launch

class ContactsListViewModel(application: Application) : AndroidViewModel(application) {
    private val contactsRepository = ContactsRepository(application)
    private val contactsListLiveData = MutableLiveData<List<Contact>>()

    val contacts: LiveData<List<Contact>>
        get() = contactsListLiveData

    fun getList() {
        viewModelScope.launch {
            try {
                contactsListLiveData.postValue(contactsRepository.getAllContacts())
            } catch (t: Throwable) {
                Log.e("viewModel", "contacts list error", t)
                contactsListLiveData.postValue(emptyList())
            }
        }
    }
}