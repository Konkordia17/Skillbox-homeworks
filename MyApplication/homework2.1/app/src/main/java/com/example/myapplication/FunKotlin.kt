package com.example.myapplication

import kotlin.math.sqrt

fun main() {

    val solutionSum = solveEquation(a = 1, b = 6, c = 5)
    println(solutionSum)


}

fun solveEquation(a: Int, b: Int, c: Int): Double? {
    val discriminant = b * b - 4 * a * c //рассчитывается дискриминант
    if (a > 0) {
        if (discriminant > 0) {
            val x1 = ((-b) + sqrt(discriminant.toDouble())) / 2 * a
            val x2 = ((-b) - sqrt(discriminant.toDouble())) / 2 * a //извлекаются корни уравнения
            return x1 + x2
        }
    }
    return null
}




