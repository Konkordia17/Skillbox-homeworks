package com.example.homework12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    lateinit var buttonANR: Button
    lateinit var nameInputEmail: EditText
    lateinit var nameInputPassword: EditText
    lateinit var checkBox: CheckBox
    lateinit var buttonLog: Button
    lateinit var textView: TextView

    var state: FormState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(tag, "onCreate")
        textView = findViewById(R.id.textView)
        buttonANR = findViewById(R.id.buttonANR)
        buttonANR.setOnClickListener {
            Thread.sleep(1000)
        }

        nameInputEmail = findViewById(R.id.nameInputEmail)
        nameInputPassword = findViewById(R.id.nameTnputPassword)
        checkBox = findViewById(R.id.Checkbox)
        buttonLog = findViewById(R.id.Button)

        buttonLog.setOnClickListener {
            clickButton()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY, state)
    }

    companion object {
        private const val KEY = "key"
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState.getParcelable(KEY)
        textView.text = state?.message
    }

    private fun clickButton() {
        if (nameInputEmail.text.toString().isNotBlank() && nameInputPassword.text.toString()
                .isNotBlank() && checkBox.isChecked
        ) {
            state = FormState(true, "")
        } else if (nameInputEmail.text.toString().isBlank() && nameInputPassword.text.toString()
                .isNotBlank() && checkBox.isChecked
        ) {
            state = FormState(false, "не введен e-mail")
        } else if (nameInputEmail.text.toString().isNotBlank() && nameInputPassword.text.toString()
                .isBlank() && checkBox.isChecked
        ) {
            state = FormState(false, "не введен пароль")
        } else if (nameInputEmail.text.toString().isNotBlank() && nameInputPassword.text.toString()
                .isNotBlank() && !checkBox.isChecked
        ) {
            state = FormState(false, "необходимо принять соглашение")
        } else {
            state = FormState(false, "не заполнены поля ввода")
        }
        textView.text = state?.message
    }
}
