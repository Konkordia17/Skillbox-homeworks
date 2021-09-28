package com.example.homework6

fun main() {
    val person1 = Person(150, 45, "Alex")
    val person2 = Person(150, 45, "Alex")
    val set = mutableSetOf(
        person1,
        person2
    )
    println("Количество человек: ${set.size}")

    set.add(Person(165, 68, "Frank"))

    set.forEach { println(it) }

    set.forEach { it.buyPet() }


}
