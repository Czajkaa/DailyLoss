package com.example.dailyloss

fun BMR(weight: Double, height: Double, age:Int, gender:String): Int {
    val value: Int = if(gender == "male") {
        (66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * age)).toInt()
    } else {
        (655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age)).toInt()
    }
    return value
}