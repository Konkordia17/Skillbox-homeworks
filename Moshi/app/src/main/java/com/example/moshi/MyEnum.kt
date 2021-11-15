package com.example.moshi

fun main() {
    qwerty { s, i -> s.length }
    println("--------------------------------------------------------")
    qwerty1 { it.length }
    println("--------------------------------------------------------")
    qwerty(block)
}

inline fun f(crossinline body: () -> Unit) {
    val f = Runnable { body() }
    // ...
}

val block: (String, Int) -> Int = { s, i ->
    s.length
}

fun qwerty(callback: (String, Int) -> Int) {
    println(callback("qwerty", 5))
}

inline fun qwerty1(callback: (String) -> Int) {
    println(callback("qwerty"))
}
