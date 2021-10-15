package com.example.homework15

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmationDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Delete item")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes", { _, _ -> })
            .setNegativeButton("No", { _, _ -> })
            .setNeutralButton("Neutral", { _, _ -> })
            .create()

    }
}