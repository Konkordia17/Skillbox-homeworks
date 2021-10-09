package com.example.homework13

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity :AppCompatActivity(R.layout.activity_second){
    lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("LifecycleTest", "SecondActivity|onCreate|${hashCode()}")
        super.onCreate(savedInstanceState)
        messageTextView = findViewById(R.id.messageTextView)
           val message =  intent.getStringExtra(KEY_MESSAGE)
        messageTextView.text = message
    }
    companion object{
        const val KEY_MESSAGE="message key"
        fun getIntent(context: Context, message: String? ): Intent {
            return Intent(context, SecondActivity::class.java)
                .putExtra(KEY_MESSAGE, message)

        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("LifecycleTest", "SecondActivity|onStart|${hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleTest", "SecondActivity|onResume|${hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleTest", "SecondActivity|onPause|${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifecycleTest", "SecondActivity|onStop|${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifecycleTest", "SecondActivity|onDestroy|${hashCode()}")
    }

}