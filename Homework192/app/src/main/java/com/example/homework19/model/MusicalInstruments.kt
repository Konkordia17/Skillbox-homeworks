package com.example.homework19.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class MusicalInstruments(
    val id: Long,
    val name: String,
    val image: String
) : Parcelable {
    class StringInstruments(
        id: Long,
        name: String,
        image: String,
        val stringCount: String,
    ) : MusicalInstruments(id, name, image)

    class WindInstruments(
         id: Long,
        name: String,
        image: String,
    ) : MusicalInstruments(id, name, image)
}