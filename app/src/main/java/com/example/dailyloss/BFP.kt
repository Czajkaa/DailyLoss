package com.example.dailyloss

fun BFP(weight: Double, height: Double, age:Int, gender:String): Int {
    val BMI = weight / (height * height) * 10000
    val value: Int = if(gender == "male") {
        (1.2 * BMI + 0.23 * age - 10.8 * 1 - 5.4).toInt()
    } else {
        (1.2 * BMI + 0.23 * age - 10.8 * 0 - 5.4).toInt()
    }
    return value
}