package com.skillbox.github.ui.repository_list

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublicRepository(
    val name: String,
    val full_name: String,
    val avatar_url: String
)
