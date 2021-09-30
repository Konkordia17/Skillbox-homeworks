package com.example.homework8

fun main() {
    println(filter(listOf(2, 3, 6, 7, 5, 3, 6)))
    println(filter(listOf(2.2, 5.3, 8.4, 7.6)))

    val queue = Queue<Int>()
//    queue.enqueue(3)
//    queue.enqueue(5)
//    queue.enqueue(6)
    println(queue)
    println(queue.dequeue())


}

fun <T : Number> filter(list: List<T>): List<T> {
    return list.filter { it.toInt() % 2 == 0 }
}

class Queue<T> {
    private val queue: MutableList<T> = mutableListOf()
    fun enqueue(item: T) {
        queue.add(item)
    }

    fun dequeue(): T? {
        return queue.removeFirstOrNull()
//        return if (queue.isEmpty()) queue.removeAt(0) else null
    }

    override fun toString(): String = queue.toString()
}








