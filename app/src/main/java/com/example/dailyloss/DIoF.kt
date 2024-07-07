package com.example.dailyloss

fun DIoF(TDEE: Int, goal: Int): Int {
    var value = 0
    when(goal) {
        0 -> value = TDEE
        1 -> value = (TDEE * 0.925).toInt()
        2 -> value = (TDEE * 0.85).toInt()
        3 -> value = (TDEE * 1.075).toInt()
        4 -> value = (TDEE * 1.15).toInt()
    }
    return value
}