package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, FirstFragment())
            .commit()

        Handler().postDelayed({
            supportFragmentManager.beginTransaction()
                .add(R.id.container, SecondFragment())
                .commit()
        }, 2000)
    }
}