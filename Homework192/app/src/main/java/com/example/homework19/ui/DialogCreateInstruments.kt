package com.example.homework19.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.homework19.CreateInstrument
import com.example.homework19.R
import com.example.homework19.model.MusicalInstruments
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlin.random.Random

open class DialogCreateInstruments : DialogFragment() {
    lateinit var stringCount: TextView
    lateinit var nameInstrument: EditText
    lateinit var imageInstrument: EditText
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(
            R.layout.instruments_list,
            null, false)
        val switch: SwitchMaterial = view.findViewById(R.id.switch1)
        nameInstrument = view.findViewById(R.id.nameInstrument)
        imageInstrument = view.findViewById(R.id.imageIns)

        stringCount = view.findViewById(R.id.stringCount)
        switch.setOnCheckedChangeListener { _, b ->
            stringCount.isVisible = b
        }

        return AlertDialog.Builder(context)
            .setView(view)
            .setPositiveButton("OK") { _, _ -> addInstrument() }
            .create()
    }

    private fun addInstrument() {
        val instrument: MusicalInstruments = if (stringCount.isVisible) {
            MusicalInstruments.StringInstruments(
                id = Random.nextLong(),
                nameInstrument.text.toString(),
                imageInstrument.text.toString(),
                stringCount.text.toString()
            )
        } else {
            MusicalInstruments.WindInstruments(
                id = Random.nextLong(),
                nameInstrument.text.toString(),
                imageInstrument.text.toString()
            )
        }
        (parentFragment as CreateInstrument).addInstrument(instrument)
    }
}