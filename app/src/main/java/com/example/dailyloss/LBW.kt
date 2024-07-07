package com.example.dailyloss

fun LBW(weight: Double, height: Double, gender:String, formula: Int, percent: Double): Double {
    var value = 0.0
    when(formula) {
        0 -> {
            value = if (gender == "male") {
                -19.2 + (0.407 * weight) + (0.267 * height)
            } else {
                -48.3 + (0.252 * weight) + (0.473 * height)
            }
        }
        1 -> {
            value = if (gender == "male") {
                1.1 * weight - 128 * (weight / height) * (weight / height)
            } else {
                1.07 * weight - 148 * (weight / height) * (weight / height)
            }
        }
        2 -> {
            value = if (gender == "male") {
                -29.5336 + (0.32810 * weight) + (0.33929 * height)
            } else {
                -43.2933 + (0.29569 * weight) + (0.41813 * height)
            }
        }
        3 -> {
            value = weight * (100 - percent) / 100
        }
    }
    return value
}