package com.example.dailyloss

import android.content.SharedPreferences

fun chceck_stats(sharedPreferences: SharedPreferences, name: String): String {

    val editor = sharedPreferences.edit()
    val weight = (sharedPreferences.getString("weight_value", "0"))!!.toDouble()
    val height = (sharedPreferences.getString("height_value", "0"))!!.toDouble()
    val age = sharedPreferences.getInt("age_value", 0)
    val gender = (sharedPreferences.getString("gender_value", "male")).toString()
    val activity = sharedPreferences.getInt("activity_value", 0)
    val goal = sharedPreferences.getInt("goal_value", 0)
    val LBM_value = (sharedPreferences.getString("LBM_value", "0"))!!.toDouble()

    val BMR_formula = sharedPreferences.getInt("BMR_formula", 0)
    val TDEE_formula = sharedPreferences.getInt("TDEE_formula", 0)
    val RMR_formula = sharedPreferences.getInt("RMR_formula", 0)
    val IBW_formula = sharedPreferences.getInt("IBW_formula", 0)
    val LBW_formula = sharedPreferences.getInt("LBW_formula", 0)
    val DWI_formula = sharedPreferences.getInt("DWI_formula", 0)
    val BFP_formula = sharedPreferences.getInt("BFP_formula", 0)

    // LBW
    val LBM = if ((LBM_value == null || LBM_value <= 0.0) && LBW_formula == 3) {
        editor.putInt("LBW_formula", 0)
        editor.apply()
        LBW(weight, height, gender, 0, LBM_value)
    } else {
        LBW(weight, height, gender, LBW_formula, LBM_value)
    }
    editor.putString("LBW_value", LBM.toString())
    editor.apply()
    // DIoF
    val DIoF: Int = if(TDEE_formula == 0) {
        DIoF(TDEE(BMR(weight, height, age, gender, BMR_formula, LBM), activity), goal)
    } else {
        DIoF(TDEE(RMR(weight, height, age, gender, RMR_formula, LBM), activity), goal)
    }
    editor.putInt("DIoF_value", DIoF)
    editor.apply()

    return when (name) {
        "BMR" -> {
            val BMR_value = BMR(weight, height, age, gender, BMR_formula, LBM).toString()
            editor.putString("BMR_value", BMR_value)
            editor.apply()
            return BMR_value
        }

        "BMI" -> {
            val BMI_value = String.format("%.1f", BMI(weight, height))
            editor.putString("BMI_value", BMI_value)
            editor.apply()
            return BMI_value
        }

        "TDEE" -> {
            val TDEE_value = if(TDEE_formula == 0) {
                TDEE(BMR(weight, height, age, gender, BMR_formula, LBM), activity).toString()
            } else {
                TDEE(RMR(weight, height, age, gender, RMR_formula, LBM), activity).toString()
            }
            editor.putString("TDEE_value", TDEE_value)
            editor.apply()
            return TDEE_value
        }

        "RMR" -> {
            val RMR_value = RMR(weight, height, age, gender, RMR_formula, LBM).toString()
            editor.putString("RMR_value", RMR_value)
            editor.apply()
            return RMR_value
        }

        "IBW" -> {
            val IBW_value = String.format("%.1f", IBW(height, gender, IBW_formula))
            editor.putString("IBW_value", IBW_value)
            editor.apply()
            return IBW_value
        }

        "LBW" -> {
            return String.format("%.1f", LBM)
        }

        "DWI" -> {
            val DWI_value = DWI(weight, activity, gender, DIoF, DWI_formula).toString()
            editor.putString("DWI_value", DWI_value)
            editor.apply()
            return DWI_value
        }

        "BFP" -> {
            val BFP_value =
                String.format("%.1f", BFP(weight, height, age, gender, BFP_formula, LBM))
            editor.putString("BFP_value", BFP_value)
            editor.apply()
            return BFP_value
        }
        "DIoF" -> {
            return DIoF.toString()
        }
        else -> {return "0"}
    }
}