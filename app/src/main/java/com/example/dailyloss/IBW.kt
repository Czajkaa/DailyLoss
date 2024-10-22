package com.example.dailyloss

fun IBW(height: Double, gender:String, formula: Int): Double {
    var value = 0.0
    if(height > 0.0) {
        when(formula) {
            0 -> {
                value = if (gender == "male") {
                    48 + 2.7 * (height - 152) / 2.54
                } else {
                    45.5 + 2.2 * (height - 152) / 2.54
                }
            }
            1 -> {
                value = if (gender == "male") {
                    50 + 2.3 * (height / 2.54 - 60)
                } else {
                    45.5 + 2.3 * (height / 2.54 - 60)
                }
            }
        }
    }

    return value
}