package com.example.homework13

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EmailActivity:AppCompatActivity(R.layout.activity_email) {
    lateinit var addressTextView: TextView
    lateinit var subjectTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressTextView = findViewById(R.id.addressTextView)
        subjectTextView = findViewById(R.id.subjectTexView)
        setEmailParamsFromIntent()
    }

    private fun setEmailParamsFromIntent() {
         val addresses = intent.getStringArrayExtra(Intent.EXTRA_EMAIL)
        val subject = intent.getStringExtra(Intent.EXTRA_SUBJECT)

        addressTextView.text = addresses?.joinToString()?: "Email address is not set"
        subjectTextView.text = subject?: "Subject is not set"
    }
}