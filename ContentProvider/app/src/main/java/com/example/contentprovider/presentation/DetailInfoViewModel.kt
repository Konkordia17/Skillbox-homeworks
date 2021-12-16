package com.example.contentprovider.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.contentprovider.data.ContactsRepository
import kotlinx.coroutines.launch

class DetailInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val contactsRepository = ContactsRepository(application)

    fun delete(id: Long) {
        viewModelScope.launch {
            try {
                contactsRepository.deleteContact(id)
            } catch (t: Throwable) {
                Log.e("add", "contacts delete error", t)
            }
        }
    }
}