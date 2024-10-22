package com.example.dailyloss

fun RMR(weight: Double, height: Double, age:Int, gender:String, formula: Int, LBM: Double): Int {
    var value = 0
    if(weight > 0.0 && height > 0.0 && age > 0) {
        when (formula) {
            0 -> {
                value = if(gender == "male") {
                    (5 + (10 * weight) + (6.25 * height) - (5 * age)).toInt()
                } else {
                    (-161 + (10 * weight) + (6.25 * height) - (5 * age)).toInt()
                }
            }
            1 -> {
                value = (500 + (22 * LBM)).toInt()
            }
        }
    }

    return value
}