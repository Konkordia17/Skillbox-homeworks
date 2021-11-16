package com.skillbox.github.ui.repository_list

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PublicRepository(
    val name: String,
    val full_name: String,
    val owner: OwnerRepository
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class OwnerRepository(val avatar_url: String, val login: String) : Parcelable
