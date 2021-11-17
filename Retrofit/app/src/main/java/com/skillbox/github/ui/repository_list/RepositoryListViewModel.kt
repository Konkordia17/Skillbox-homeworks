package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListViewModel : ViewModel() {
    private val repositoriesLiveData = MutableLiveData<List<PublicRepository>>()
    val repositories: LiveData<List<PublicRepository>>
        get() = repositoriesLiveData

    fun getRepositoryList() {
        Networking.githubApi.getRepositoriesList()
            .enqueue(object : Callback<List<PublicRepository>> {
                override fun onResponse(
                    call: Call<List<PublicRepository>>,
                    response: Response<List<PublicRepository>>
                ) {
                    val repositories = response.body()
                    repositoriesLiveData.value = repositories
                }

                override fun onFailure(call: Call<List<PublicRepository>>, t: Throwable) {
                }
            })
    }
}
