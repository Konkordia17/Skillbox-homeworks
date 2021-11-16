package com.skillbox.github.data

import com.skillbox.github.ui.current_user.User
import com.skillbox.github.ui.repository_list.PublicRepository
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GithubApi {
    @GET("/user")
    fun getUserInfo(): Call<User>

    @GET("/repositories")
    fun getRepositoriesList(): Call<List<PublicRepository>>

    @GET("/user/starred/{owner}/{repo}")
    fun checkIsFavourite(@Path("owner") owner: String, @Path("repo") repo: String)
            : Call<Void>

    @PUT("/user/starred/{owner}/{repo}")
    fun putToFavourites(@Path("owner") owner: String, @Path("repo") repo: String)
            : Call<Void>

    @DELETE("/user/starred/{owner}/{repo}")
    fun removeFromFavourites(@Path("owner") owner: String, @Path("repo") repo: String)
            : Call<Void>
}
