package com.skillbox.github.ui.detail_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.Networking
import com.skillbox.github.ui.repository_list.PublicRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailInfoViewModel : ViewModel() {
    var addedToRepository = MutableLiveData<Boolean>()

    fun checkRepository(repository: PublicRepository) {
        Networking.githubApi.checkIsFavourite(repository.owner.login, repository.name)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    addedToRepository.value = response.code() == 204
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    addedToRepository.value = false
                }
            })
    }

    private fun removeFromFavourites(repository: PublicRepository) {
        Networking.githubApi
            .removeFromFavourites(repository.owner.login, repository.name)
            .enqueue(
                object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        val isDeleted = response.code() == 204
                        addedToRepository.value = !isDeleted
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {

                    }
                })
    }

    private fun putToFavourites(repository: PublicRepository) {
        Networking.githubApi
            .putToFavourites(repository.owner.login, repository.name)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    addedToRepository.value = response.code() == 204
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                }
            })
    }

    fun putOrRemoveRepository(repository: PublicRepository) {
        when (addedToRepository.value) {
            true -> removeFromFavourites(repository)
            false -> putToFavourites(repository)
        }
    }
}