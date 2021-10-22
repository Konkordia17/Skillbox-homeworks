package com.example.listhomework

import android.widget.EditText
import android.widget.TextView

sealed class MusicalInstruments {
    data class StringInstruments(
        val id: Long,
        val name: String,
        val image: String,
        val stringCount: String,
    ) : MusicalInstruments()

    data class WindInstruments(
        val id: Long,
        val name: String,
        val image: String,
    ) : MusicalInstruments()
}