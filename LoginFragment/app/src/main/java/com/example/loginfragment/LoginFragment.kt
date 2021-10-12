package com.example.loginfragment

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.activity_login) {
    private val navigator: Navigator?
        get() {
            return activity?.let { it as? Navigator }

        }
    lateinit var buttonANR: Button
    lateinit var nameInputEmail: EditText
    lateinit var nameInputPassword: EditText
    lateinit var checkBox: CheckBox
    lateinit var buttonLog: Button
    lateinit var textView: TextView
    var state: FormState? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView = requireView().findViewById(R.id.textView)
        buttonANR = requireView().findViewById(R.id.buttonANR)
        nameInputEmail = requireView().findViewById(R.id.nameInputEmail)
        nameInputPassword = requireView().findViewById(R.id.nameTnputPassword)
        checkBox = requireView().findViewById(R.id.Checkbox)
        buttonLog = requireView().findViewById(R.id.Button)

        buttonANR.setOnClickListener {
            Thread.sleep(1000)
        }

        buttonLog.setOnClickListener {
            clickButton()
            navigator?.navigateTo(MainFragment())
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY, state)
    }

    companion object {
        private const val KEY = "key"
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable(KEY)
        }
        textView.text = state?.message
    }
}