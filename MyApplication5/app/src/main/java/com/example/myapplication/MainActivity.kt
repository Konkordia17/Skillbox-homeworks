package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            viewModel.increment()
        }

        viewModel.liveInt.observe(this) {
            when (it) {
                Color.RED -> // стоять
                Color.GREEN -> // ехать
                Color.YELLOW -> // дрочить
                else -> {}
            }
        }

        viewModel.liveInt.observe(this) {

        }

        viewModel.liveInt.observe(this) {

        }

        viewModel.liveInt.observe(this) {

        }

        viewModel.liveInt.observe(this) {
            liveInt.text = it.toString()
        }
    }
}