package com.example.homework15

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity(R.layout.activity_dialog) {
    private var dialog:AlertDialog? = null

    lateinit var showSimpleDialogButton: Button
    lateinit var showDialogFragment: Button
    lateinit var showButtonDialog: Button
    lateinit var showButtomSheetDialog: Button
    lateinit var showSingleChoiceDialogButton: Button
    lateinit var showCustomLayoutDialogButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSimpleDialogButton = findViewById(R.id.showSimpleDialogButton)
        showButtonDialog = findViewById(R.id.showButtonDialog)
        showDialogFragment = findViewById(R.id.showDialogFragment)
        showCustomLayoutDialogButton = findViewById(R.id.showCustomLayoutDialogButton)
        showButtomSheetDialog = findViewById(R.id.showBottomSheetDialogFragment)
        showSingleChoiceDialogButton = findViewById(R.id.showSingleChoiceDialogButton)
        showSimpleDialogButton.setOnClickListener {
            showSimpleDialog()
        }
        showButtonDialog.setOnClickListener { showDialogWithButtons() }
        showSingleChoiceDialogButton.setOnClickListener {
            showDialogWithSingleChoice()
        }
        showCustomLayoutDialogButton.setOnClickListener {
            showDialogWithCustomLayout()
        }
        showDialogFragment.setOnClickListener {
            showDialogFragment()
        }
        showButtomSheetDialog.setOnClickListener {
            showButtomSheetDialog()
        }

    }
    private fun showDialogFragment(){
        ConfirmationDialogFragment()
            .show(supportFragmentManager,"confirmationDialogTag")
    }

    private fun hideDialog(){
        supportFragmentManager.findFragmentByTag("confirmationDialogTag")?.let {
            it as? ConfirmationDialogFragment}
            ?.dismiss()
    }

    private fun showSimpleDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("SimpleDialog")
            .setMessage("SimpleDialogMessage")
            .create()
        dialog.show()
    }
    private fun showButtomSheetDialog(){
        AvatarDialogFragment().show(supportFragmentManager, "tag")

    }

    private fun showDialogWithButtons() {
        AlertDialog.Builder(this)
            .setTitle("Delete item")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes", { _, _ -> toast("Clicked yes") })
            .setNegativeButton("No", { _, _ -> toast("Clicked no") })
            .setNeutralButton("Neutral", { _, _ -> toast("Clicked neutral") })
            .create()
            .show()
    }

    private fun showDialogWithSingleChoice() {
        val mailProvider = arrayOf("google", "yandex", "mailru", "rambler")
        AlertDialog.Builder(this)
            .setTitle("Select mail provider")
            .setItems(mailProvider) { _, which -> toast("Selected = ${mailProvider[which]}") }
            .show()
    }

    private fun showDialogWithCustomLayout() {
       dialog = AlertDialog.Builder(this)
            .setView(R.layout.dialog_attention)
            .setPositiveButton("Ok"){_,_->}
            .show()
    }
    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

}