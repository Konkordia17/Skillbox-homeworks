package com.example.myapplication

fun main() {
    val firstName = "Ksenia"
    val lastName = "Karamysheva"
    var height = 167
    var weight = 68.0f
    var isChild: Boolean = (height < 150 || weight < 40)
    var info =
        "My name is $firstName, my lastname is $lastName, my weight is $weight, my height is $height, I'm a child = $isChild"
    println(info)
    height = 140
    isChild = (height < 150 || weight < 40)
    info =
        "My name is $firstName, my lastname is $lastName, my weight is $weight, my height is $height, I'm a child = $isChild"

    print(info)


}

