package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val liveInt = MutableLiveData<Color>(Color.RED)
    var oldInt = 0

    fun increment() {
        liveInt.value = (liveInt.value ?: 0) + 1
        oldInt += 1
    }

    fun otherFun() {
        oldInt += 1
    }
}

enum Color {
    RED,
    GREEN,
    YELLOW
}