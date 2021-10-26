package com.example.homework18

import org.threeten.bp.Instant


data class Location(
    val id: Long,
    val info: String,
    var time:Instant,
    val image: String
)