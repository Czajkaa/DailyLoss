package com.example.dailyloss

fun Macro(DIoF: Int, macro_formula: Int): Triple<Int, Int, Int> {
    var protein = 0
    var carbs = 0
    var fats = 0
    when(macro_formula) {
        0 -> {
            protein = (DIoF * 0.20).toInt()
            carbs = (DIoF * 0.50).toInt()
            fats = (DIoF * 0.30).toInt()
        }
        1 -> {
            protein = (DIoF * 0.25).toInt()
            carbs = (DIoF * 0.50).toInt()
            fats = (DIoF * 0.25).toInt()
        }
        2 -> {
            protein = (DIoF * 0.25).toInt()
            carbs = (DIoF * 0.60).toInt()
            fats = (DIoF * 0.15).toInt()
        }
        3 -> {
            protein = (DIoF * 0.40).toInt()
            carbs = (DIoF * 0.25).toInt()
            fats = (DIoF * 0.35).toInt()
        }
    }
    return Triple(protein, carbs, fats)
}
