package com.example.homework131

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText


    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = findViewById(R.id.loginButton)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        passwordEditText.doOnTextChanged { _, _, _, _ -> clickButton() }


        loginButton.setOnClickListener {
            val activityClass = SecondActivity::class.java
            val activityIntent = Intent(this, activityClass)
            startActivity(activityIntent)
            finish()
        }
    }

    private fun clickButton() {
        loginButton.isEnabled = emailEditText.text.toString().isNotBlank() && passwordEditText.text.toString()
            .isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }



}