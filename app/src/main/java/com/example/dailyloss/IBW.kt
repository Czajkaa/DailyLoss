package com.example.dailyloss

fun IBW(height: Double, gender:String): Double {
    val value: Double = if (gender == "male") {
        (48 + 1.1 * (height - 152))
    } else {
        (45.4 + 0.9 * (height - 152))
    }
    return value
}