package com.skillbox.github.ui.current_user

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentUserViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    val errorText = MutableLiveData<String>()

    fun getUser() {
        Networking.githubApi.getUserInfo().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val newUser = response.body()
                user.value = newUser
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                errorText.value = "Ошибка соединения"
            }
        })
    }
}