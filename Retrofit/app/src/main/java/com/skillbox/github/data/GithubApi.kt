package com.skillbox.github.data

import com.skillbox.github.ui.current_user.User
import com.skillbox.github.ui.repository_list.PublicRepository
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("/user")
    fun getUserInfo(): Call<User>

    @GET("/repositories")
    fun getRepositoriesList(): Call<List<PublicRepository>>
}