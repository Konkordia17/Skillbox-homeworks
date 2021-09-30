package com.example.homework8

import java.lang.Error

sealed class Result<out T, R> {

    data class Success<T, R>(val value: T) : Result<T, R>()
    data class Error<T, R>(val typeError: R) : Result<T, R>()
}

fun returnResult(isError: Boolean): Result<Int, String> {
    return if (isError) Result.Error("Error") else
        Result.Success(100)
}


//Создайте sealed-class Result, который параметризован двумя типами — типом успешного результата (T) и типом ошибки (R). Наследуйтесь от него двумя классами:
//Success<T, R> - data class, принимает в конструктор объект типа T;
//Error<T, R> - data class, принимает в конструктор объект типа R.
//Создайте функцию, которая возвращает объект типа Result<Int, String>. Сделайте так, чтобы результат функции можно было присвоить переменным со следующими типами:
//Result<Number, String>
//Result<Any, String>
//И нельзя было присвоить переменным со следующими типами:
//
//Result<Int, CharSequence>
//Result<Int, Any>
//то есть класс Result должен быть ковариантным по параметру T и инвариантным по параметру R.

fun main() {

    val result1: Result<Number, String>
    result1 = returnResult(true)
    println(result1)

    val result3: Result<Int, CharSequence>
    result3 = returnResult(true) // несоответствие типов



}