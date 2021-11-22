package com.skillbox.github.ui.repository_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.Networking
import kotlinx.coroutines.*

class RepositoryListViewModel : ViewModel() {
    private val repositoriesLiveData = MutableLiveData<List<PublicRepository>>()

    val repositories: LiveData<List<PublicRepository>>
        get() = repositoriesLiveData

    fun getListWithCoroutine() {
        viewModelScope.launch {
            try {
                val repositoryList = withContext(Dispatchers.IO) {
                    Networking.githubApi.getRepositoriesList().execute().body()
                }
                repositoriesLiveData.value = repositoryList
            } catch (t: Throwable) {
            }
        }
    }
}
