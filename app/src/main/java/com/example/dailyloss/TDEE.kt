package com.example.dailyloss

fun TDEE(BMR:Int, activity: Int): Int {
    var value = 0
    when(activity) {
        0 -> value = (BMR * 1.2).toInt()
        1 -> value = (BMR * 1.375).toInt()
        2 -> value = (BMR * 1.55).toInt()
        3 -> value = (BMR * 1.725).toInt()
        4 -> value = (BMR * 1.9).toInt()
    }

    return value
}
