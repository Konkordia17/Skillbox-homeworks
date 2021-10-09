package com.example.homework131


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    lateinit var callButton: Button
    lateinit var telephoneEditText: EditText
    lateinit var resultCallNumber: TextView

    private val callLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                resultCallNumber.text = telephoneEditText.text.toString()
            } else {
                toast("Звонок отменен")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callButton = findViewById(R.id.callNumberButton)
        telephoneEditText = findViewById(R.id.telephoneEditText)
        resultCallNumber = findViewById(R.id.resultCallNumber)

        telephoneEditText.doOnTextChanged { _, _, _, _ -> checkNumber() }

        callButton.setOnClickListener {
            val telephoneNumber = telephoneEditText.text.toString()
            dialPhoneNumber(telephoneNumber)
        }
    }

    private fun checkNumber() {
        callButton.isEnabled = Patterns.PHONE.matcher(telephoneEditText.text).matches()

    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (phoneIntent.resolveActivity(packageManager) != null) {
            startActivity(phoneIntent)
        } else {
            toast("Приложение не установлено")
        }
        callLauncher.launch(phoneIntent)

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
