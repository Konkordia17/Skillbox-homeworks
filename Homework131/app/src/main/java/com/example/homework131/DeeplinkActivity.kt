package com.example.homework131

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeeplinkActivity :AppCompatActivity(R.layout.activity_deeplink){
    lateinit var textView: TextView
//    https://go.skillbox.ru/education/course/android-dev-1/5e069f51-0613-4402-9737-c807cb103cde/homework

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = findViewById(R.id.textView)
        handleIntentData()
    }

    private fun handleIntentData(){
        intent.data?.lastPathSegment?.let {
            textView.text = it
        }

    }
}