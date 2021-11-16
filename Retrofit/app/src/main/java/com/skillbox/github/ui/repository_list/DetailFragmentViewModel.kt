package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragmentViewModel: ViewModel() {
    var addedToRepository = MutableLiveData<Boolean>()

    fun checkRepository(repository:PublicRepository){
        Networking.githubApi.checkIsFavourite(repository.owner.login, repository.name)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    addedToRepository.value
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

}