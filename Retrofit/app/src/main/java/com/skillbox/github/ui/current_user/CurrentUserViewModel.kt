package com.skillbox.github.ui.current_user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.github.data.Networking
import kotlinx.coroutines.*

class CurrentUserViewModel : ViewModel() {
    val userLiveData = MutableLiveData<User>()
    val errorText = MutableLiveData<String>()
    val followersListLiveData = MutableLiveData<List<User>>()
    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Error", "Error from CoroutineExceptionHandler", throwable)
    }
    private val scope = CoroutineScope(Dispatchers.Main + errorHandler)

    fun getUserAndFollowers() {
        scope.launch {
            val user = async {
                Networking.githubApi.getUserInfo()
            }

            val followers = async {
                Networking.githubApi.getFollowersList()
            }
            userLiveData.value = user.await()
            followersListLiveData.value = followers.await()
        }
    }

}
