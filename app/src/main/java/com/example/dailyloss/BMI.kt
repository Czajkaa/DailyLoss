package com.example.dailyloss

fun BMI(weight: Double, height: Double): Double {
    return weight / (height * height) * 10000
}