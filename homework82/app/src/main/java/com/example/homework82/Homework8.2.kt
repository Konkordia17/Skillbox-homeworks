package com.example.homework82

fun main() {
    val queue = Queue<Int>(mutableListOf(5, 3, 7, -12, 5, 9, -7))
    println(queue)
    val queueFunction = queue.filter { it > 0 }
    println(queueFunction)
    val queueLambda = queue.filter(::queueFilter)
}

class Queue<T>(list: List<T>) {
    private var queue: MutableList<T> = list as MutableList<T>
    fun enqueue(item: T) {
        queue.add(item)
    }

    fun dequeue(): T? {
        return queue.removeFirstOrNull()
    }

    override fun toString(): String = queue.toString()

    fun filter(element:(item:T)-> Boolean): Queue<T>{
        val newQueue = queue.filter(element)
        return Queue(newQueue)
    }
}
fun queueFilter(item: Int): Boolean{
    return item>0
}


//Используйте класс Queue из части 2 предыдущего домашнего задания.
//Добавьте метод «фильтр», который принимает функцию фильтрации элементов и возвращает новый объект очереди с отфильтрованными элементами.
//Используйте вызов функции filter с использованием лямбда-выражения и ссылки на функцию.