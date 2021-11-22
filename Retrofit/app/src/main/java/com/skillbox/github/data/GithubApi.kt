package com.skillbox.github.data

import android.hardware.usb.UsbRequest
import com.skillbox.github.ui.current_user.User
import com.skillbox.github.ui.repository_list.PublicRepository
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {
    @GET("/user")
    suspend fun getUserInfo(): User

    @GET("/repositories")
    fun getRepositoriesList(): Call<List<PublicRepository>>

    @GET("/user/starred/{owner}/{repo}")
    fun checkIsFavourite(@Path("owner") owner: String, @Path("repo") repo: String)
            : Call<Void>

    @PUT("/user/starred/{owner}/{repo}")
    suspend fun putToFavourites(@Path("owner") owner: String, @Path("repo") repo: String)
            : Response<Unit>

    @DELETE("/user/starred/{owner}/{repo}")
    suspend fun removeFromFavourites(@Path("owner") owner: String, @Path("repo") repo: String)
            : Response<Unit>

    @GET("/user/followers")
    suspend fun getFollowersList(): List<User>
}
