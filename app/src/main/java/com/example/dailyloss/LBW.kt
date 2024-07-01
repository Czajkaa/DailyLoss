package com.example.dailyloss

fun LBW(weight: Double, height: Double, gender:String): Double {
    val value: Double = if (gender == "male") {
        (-19.2 + (0.407 * weight) + (0.267 * height))
    } else {
        (-48.3 + (0.252 * weight) + (0.473 * height))
    }
    return value
}