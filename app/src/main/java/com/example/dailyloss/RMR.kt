package com.example.dailyloss

fun RMR(weight: Double, height: Double, age:Int, gender:String): Int {
    val value: Int = if(gender == "male") {
        (5 + (10 * weight) + (6.25 * height) - (5 * age)).toInt()
    } else {
        (-16 + (10 * weight) + (6.25 * height) - (5 * age)).toInt()
    }
    return value
}