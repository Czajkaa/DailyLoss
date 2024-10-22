package com.example.dailyloss

fun BMI(weight: Double, height: Double): Double {
    var bmi = 0.0
    if(weight > 0.0 && height > 0.0) {
        bmi = weight / (height * height) * 10000
    }
    return bmi
}