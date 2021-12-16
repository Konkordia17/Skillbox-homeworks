package com.example.contentprovider.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contentprovider.data.ContactsRepository
import kotlinx.coroutines.launch

class AddContactViewModel(application: Application) : AndroidViewModel(application) {

    private val contactsRepository = ContactsRepository(application)
    private val addContactsListLiveData = MutableLiveData<Unit>()

    fun save(name: String, phone: String, email: String?) {
        viewModelScope.launch {
            try {
                contactsRepository.saveContact(name, phone, email)
                addContactsListLiveData.postValue(Unit)
            } catch (t: Throwable) {
                Log.e("add", "contacts add error", t)
            }
        }

    }

}