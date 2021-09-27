package com.example.homework4

fun main() {

    println("Введите число: ")
    val quantity = inputNumber()

    println("Введите номера телефонов: ")
    val listOfPhones = getPhones(quantity)
    println(listOfPhones)
    println("Номера, начинающиеся c +7: ${listOfPhones.filter { it.startsWith("+7") }}")
    val setOfNumbers = listOfPhones.toSet()
    println("Количество уникальных номеров: ${setOfNumbers.size}")
    println("Сумма длин всех номеров = ${listOfPhones.sumBy { it.length }}")
    val mutableMap = getContacts(setOfNumbers)

    mutableMap.forEach { (key, value) ->
        println("Человек: $value. Номер телефона: $key")
    }
}

fun getPhones(n: Int): List<String> {
    val mutableList = mutableListOf<String>()
    repeat(n) {
        val number = readLine().toString()
        mutableList.add(number)
    }
    return mutableList
}

fun getContacts(set: Set<String>): Map<String, String> {
    val mutableMap = mutableMapOf<String, String>()
    set.forEach {
        print("Введите имя человека с номером телефона $it: ")
        val value = readLine().toString()
        mutableMap[it] = value

    }
    return mutableMap
}


fun inputNumber(): Int {
    return readLine()?.toIntOrNull() ?: inputNumber()
}


