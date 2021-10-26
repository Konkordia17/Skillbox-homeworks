package com.example.homework18

import org.threeten.bp.Instant

data class Message(
    val id: Long,
    val text: String,
    val createdAt: Instant
) {

}