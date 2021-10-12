package com.example.homework14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var showFragmentButton: Button
    lateinit var replaceFragmentButton: Button
    lateinit var dataEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragmentButton = findViewById(R.id.showFragmentButton)
        replaceFragmentButton = findViewById(R.id.replaceFragmentButton)
        dataEditText = findViewById(R.id.dataEditText)
        showFragmentButton.setOnClickListener {
            showInfoFragment()
        }

        replaceFragmentButton.setOnClickListener {
            replaceInfoFragment()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(!supportFragmentManager.isStateSaved){
            showInfoFragment()
        }
    }

    private fun showInfoFragment() {
        val alreadyHasFragment = supportFragmentManager.findFragmentById(R.id.container) != null

        if (!alreadyHasFragment) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, InfoFragment.newInstance(dataEditText.text.toString()))
                .commit()
        } else {
            toast("Fragment iis shown")
        }
    }

    private fun replaceInfoFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, InfoFragment.newInstance(dataEditText.text.toString()))
            .commit()
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}