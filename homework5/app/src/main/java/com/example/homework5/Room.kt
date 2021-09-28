package com.example.homework5

import android.icu.text.CaseMap

open class Room(val area: Double) {
    protected open val title: String = "Обычная комната"
    fun getDescription() = println("Комната: $title, площадь: $area кв.метра")
}

fun main() {
    val room = Room(15.0)
    room.getDescription()
    val bedroom = Bedroom(15.3)
    bedroom.getDescription()


}

