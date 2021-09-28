package com.example.homework6

import kotlin.random.Random

class Person(val height: Int, val weight: Int, val name: String) {

    var pets: HashSet<Animal> = hashSetOf()

    fun buyPet() {
        val alphabet: List<Char> = ('a'..'z') + ('A'..'Z')

        val randomWord: String = List((1..10).random()) { alphabet.random() }.joinToString("")

        pets.add(Animal(Random.nextInt(), Random.nextInt(), randomWord))
        println(pets)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (height != other.height) return false
        if (weight != other.weight) return false
        if (name != other.name) return false
        if (pets != other.pets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + weight
        result = 31 * result + name.hashCode()
        result = 31 * result + pets.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(height=$height, weight=$weight, name='$name', pets=$pets)"
    }


}


