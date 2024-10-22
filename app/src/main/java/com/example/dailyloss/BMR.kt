package com.example.dailyloss

fun BMR(weight: Double, height: Double, age:Int, gender:String, formula: Int, LBM: Double): Int {
    var value = 0

    if(weight > 0.0 && height > 0.0 && age > 0) {
        when(formula) {
            0 -> {
                value = if(gender == "male") {
                    (66.5 + (13.75 * weight) + (5.003 * height) - (6.75 * age)).toInt()
                } else {
                    (655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age)).toInt()
                }
            }
            1 -> {
                value = (370 + (21.6 * LBM)).toInt()
            }
            2 -> {
                if(gender == "male") {
                    if(age in 18..29) value = (15.057 * weight + 692.2).toInt()
                    else if (age in 30..59) value = (11.472 * weight + 873.1).toInt()
                    else if (age >= 60) value = (11.711 * weight + 587.7).toInt()
                } else {
                    if(age in 18..29) value = (14.818 * weight + 486.6).toInt()
                    else if (age in 30..59) value = (8.126 * weight + 845.6).toInt()
                    else if (age >= 60) value = (9.082 * weight + 658.5).toInt()
                }
            }
        }
    }
    return value
}