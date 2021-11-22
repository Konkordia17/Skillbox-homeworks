package com.skillbox.github.ui.detail_fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.Networking
import com.skillbox.github.ui.repository_list.PublicRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DetailInfoViewModel : ViewModel() {
    var addedToRepository = MutableLiveData<Boolean>()

    private suspend fun checkRepository(repository: PublicRepository): Boolean {
        val call = Networking.githubApi.checkIsFavourite(repository.owner.login, repository.name)

        return suspendCancellableCoroutine {
            it.invokeOnCancellation {
                call.cancel()
            }
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    addedToRepository.value = response.code() == 204
                    it.resume(true)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }

    fun check(repository: PublicRepository) {
        viewModelScope.launch {
            checkRepository(repository)
        }
    }

    private fun removeFromFavourites(repository: PublicRepository) {
        viewModelScope.launch {
            try {
                val isRemovedFromFavourite = Networking.githubApi
                    .removeFromFavourites(repository.owner.login, repository.name).code() == 204
                addedToRepository.value = !isRemovedFromFavourite
            } catch (t: Throwable) {
            }
        }
    }

    private fun putToFavourites(repository: PublicRepository) {
        viewModelScope.launch {
            try {
                val isAddedToFavourite = Networking.githubApi
                    .putToFavourites(repository.owner.login, repository.name).code() == 204
                addedToRepository.value = isAddedToFavourite

            } catch (t: Throwable) {
                Log.d("asdf", "${t.message}")
            }
        }
    }

    fun putOrRemoveRepository(repository: PublicRepository) {
        when (addedToRepository.value) {
            true -> removeFromFavourites(repository)
            false -> putToFavourites(repository)
        }
    }
}