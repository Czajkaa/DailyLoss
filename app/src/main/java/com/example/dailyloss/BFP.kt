package com.example.dailyloss

fun BFP(weight: Double, height: Double, age:Int, gender:String, formula: Int, LBM: Double): Double {
    val BMI = weight / (height * height) * 10000
    var value = 0.0
    when(formula) {
        0 -> {
            value = if(gender == "male") {
                1.2 * BMI + 0.23 * age - 10.8 * 1 - 5.4
            } else {
                1.2 * BMI + 0.23 * age - 10.8 * 0 - 5.4
            }
        }
        1 -> {
            value = (weight - LBM) / weight * 100
        }
    }
    return value
}