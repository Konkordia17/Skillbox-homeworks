package com.example.converter.data

import com.example.converter.data.model.ValCurs
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("scripts/xml_daily.asp")
    fun getValutesList(): Call<ValCurs>
}