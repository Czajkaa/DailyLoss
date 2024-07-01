package com.example.dailyloss

fun TDEE(BMR:Int, goal: Int): Int {
    val value: Int
    if(goal == 0) {
        value = (BMR * 1.2).toInt()
    } else if(goal == 1) {
        value = (BMR * 1.375).toInt()
    } else if(goal == 2) {
        value = (BMR * 1.55).toInt()
    } else if(goal == 3) {
        value = (BMR * 1.725).toInt()
    } else {
        value = (BMR * 1.9).toInt()
    }
    return value
}
