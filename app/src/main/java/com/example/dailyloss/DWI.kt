package com.example.dailyloss

fun DWI(weight: Double, activity: Int, gender: String, kcal: Int, formula: Int): Int {
    var value = 0
    if(weight > 0.0) {
        when(formula) {
            0 -> {
                value = (weight / 30 * 1000).toInt()
            }
            1 -> {
                value = kcal
            }
            2 -> {
                value = if (gender == "male") {
                    3700
                } else {
                    2700
                }
            }
            3 -> {
                when(activity) {
                    0 -> value = ((weight * 30) + (0 * 12 * 30)).toInt()
                    1 -> value = ((weight * 30) + (1 * 12 * 30)).toInt()
                    2 -> value = ((weight * 30) + (2 * 12 * 30)).toInt()
                    3 -> value = ((weight * 30) + (2.5 * 12 * 30)).toInt()
                    4 -> value = ((weight * 30) + (3 * 12 * 30)).toInt()
                }
            }
        }
    }

    return value
}