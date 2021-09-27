package com.example.myapplication

fun main() {
    var positiveNumbers = 0
    var sum = 0
    println("Введите число:")
    val n = inputNumber()
    println("Число: $n")
    repeat(n) {
        val number = inputNumber()
        if (number > 0) positiveNumbers++
        sum += number
    }
    println("Количество положительных чисел: $positiveNumbers")
    println("Сумма введенных чисел = $sum")
}

fun inputNumber(): Int {
    return readLine()?.toIntOrNull() ?: inputNumber()
}
