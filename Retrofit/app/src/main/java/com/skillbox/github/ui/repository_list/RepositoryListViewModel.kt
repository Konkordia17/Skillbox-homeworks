package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepositoryListViewModel : ViewModel() {
    private val listRepository = ListRepository()
    val repositoriesliveData = MutableLiveData<List<PublicRepository>>()

    val repositorieLiveData: LiveData<List<PublicRepository>>
        get() = repositoriesliveData

    fun getRepositoriesList() {
        repositoriesliveData.postValue(repositoriesliveData.value.orEmpty())

    }
}
