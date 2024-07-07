package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.ceil

class Stats_Macro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_macro)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Formulas

        val formula_1 = findViewById<Button>(R.id.macro_formula_1)
        val formula_2 = findViewById<Button>(R.id.macro_formula_2)
        val formula_3 = findViewById<Button>(R.id.macro_formula_3)
        val formula_4 = findViewById<Button>(R.id.macro_formula_4)

        // Select formula

        val macro_formula = sharedPreferences.getInt("macro_formula", 0)
        when(macro_formula) {
            0 -> formula_1.isSelected = true
            1 -> formula_2.isSelected = true
            2 -> formula_3.isSelected = true
            3 -> formula_4.isSelected = true
        }

        // Change DIoF

        val DIoF_value = findViewById<TextView>(R.id.macro_value)
        val DIoF = sharedPreferences.getInt("DIoF_value", 0)
        DIoF_value.text = DIoF.toString()

        formula_1.setOnClickListener {
            formula_1.isSelected = true
            formula_2.isSelected = false
            formula_3.isSelected = false
            formula_4.isSelected = false
            editor.putInt("macro_formula", 0)
            editor.apply()
            updateMacro(DIoF)
        }
        formula_2.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = true
            formula_3.isSelected = false
            formula_4.isSelected = false
            editor.putInt("macro_formula", 1)
            editor.apply()
            updateMacro(DIoF)
        }
        formula_3.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = false
            formula_3.isSelected = true
            formula_4.isSelected = false
            editor.putInt("macro_formula", 2)
            editor.apply()
            updateMacro(DIoF)
        }
        formula_4.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = false
            formula_3.isSelected = false
            formula_4.isSelected = true
            editor.putInt("macro_formula", 3)
            editor.apply()
            updateMacro(DIoF)
        }
        updateMacro(DIoF)
    }

    @SuppressLint("SetTextI18n")
    fun updateMacro(DIoF: Int) {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val macro_formula = sharedPreferences.getInt("macro_formula", 0)

        // Value

        val (proteinValue, carbsValue, fatsValue) = Macro(DIoF, macro_formula)
        val protein = findViewById<TextView>(R.id.macro_proteinValue)
        val carbs = findViewById<TextView>(R.id.macro_carbsValue)
        val fats = findViewById<TextView>(R.id.macro_fatsValue)
        val protein_kcal = findViewById<TextView>(R.id.macro_proteinValueKcal)
        val carbs_kcal = findViewById<TextView>(R.id.macro_carbsValueKcal)
        val fats_kcal = findViewById<TextView>(R.id.macro_fatsValueKcal)

        editor.putInt("protein_g", proteinValue/4)
        editor.putInt("carbs_g", carbsValue/4)
        editor.putInt("fats_g", fatsValue/9)
        editor.apply()

        protein.text = (proteinValue/4).toString()
        carbs.text = (carbsValue/4).toString()
        fats.text = (fatsValue/9).toString()
        protein_kcal.text = proteinValue.toString()
        carbs_kcal.text = carbsValue.toString()
        fats_kcal.text = fatsValue.toString()

        // Graph

        val proteinGraph = findViewById<ProgressBar>(R.id.macro_graphProtein)
        val carbsGraph = findViewById<ProgressBar>(R.id.macro_graphCarbs)
        val fatsGraph = findViewById<ProgressBar>(R.id.macro_graphFats)
        val proteinPercent = findViewById<TextView>(R.id.macro_proteinProgressText)
        val fatsPercent = findViewById<TextView>(R.id.macro_fatsProgressText)
        val carbsPercent = findViewById<TextView>(R.id.macro_carbsProgressText)

        proteinPercent.text = (ceil(proteinValue.toDouble() / DIoF.toDouble() * 100)).toInt().toString() + "%"
        fatsPercent.text = (ceil(fatsValue.toDouble() / DIoF.toDouble() * 100)).toInt().toString() + "%"
        carbsPercent.text = (ceil(carbsValue.toDouble() / DIoF.toDouble() * 100)).toInt().toString() + "%"

        val scale = resources.displayMetrics.density
        val proteinParams = proteinPercent.layoutParams as ViewGroup.MarginLayoutParams
        val fatsParams = fatsPercent.layoutParams as ViewGroup.MarginLayoutParams
        val carbsParams = carbsPercent.layoutParams as ViewGroup.MarginLayoutParams

        var top: Int
        var bottom: Int
        var start: Int
        var end: Int

        when(macro_formula) {
            0 -> {
                proteinGraph.progress = 20
                fatsGraph.progress = 50
                carbsGraph.progress = 100

                top = (0 * scale + 0.5f).toInt()
                bottom = (130 * scale + 0.5f).toInt()
                start = (90 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                proteinParams.topMargin = top
                proteinParams.bottomMargin = bottom
                proteinParams.leftMargin = start
                proteinParams.rightMargin = end

                top = (80 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (140 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                fatsParams.topMargin = top
                fatsParams.bottomMargin = bottom
                fatsParams.leftMargin = start
                fatsParams.rightMargin = end

                top = (0 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (0 * scale + 0.5f).toInt()
                end = (150 * scale + 0.5f).toInt()
                carbsParams.topMargin = top
                carbsParams.bottomMargin = bottom
                carbsParams.leftMargin = start
                carbsParams.rightMargin = end
            }
            1 -> {
                proteinGraph.progress = 25
                fatsGraph.progress = 50
                carbsGraph.progress = 100

                top = (0 * scale + 0.5f).toInt()
                bottom = (110 * scale + 0.5f).toInt()
                start = (120 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                proteinParams.topMargin = top
                proteinParams.bottomMargin = bottom
                proteinParams.leftMargin = start
                proteinParams.rightMargin = end

                top = (110 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (114 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                fatsParams.topMargin = top
                fatsParams.bottomMargin = bottom
                fatsParams.leftMargin = start
                fatsParams.rightMargin = end

                top = (0 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (0 * scale + 0.5f).toInt()
                end = (150 * scale + 0.5f).toInt()
                carbsParams.topMargin = top
                carbsParams.bottomMargin = bottom
                carbsParams.leftMargin = start
                carbsParams.rightMargin = end
            }
            2 -> {
                proteinGraph.progress = 25
                fatsGraph.progress = 40
                carbsGraph.progress = 100

                top = (0 * scale + 0.5f).toInt()
                bottom = (110 * scale + 0.5f).toInt()
                start = (120 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                proteinParams.topMargin = top
                proteinParams.bottomMargin = bottom
                proteinParams.leftMargin = start
                proteinParams.rightMargin = end

                top = (70 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (140 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                fatsParams.topMargin = top
                fatsParams.bottomMargin = bottom
                fatsParams.leftMargin = start
                fatsParams.rightMargin = end

                top = (20 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (0 * scale + 0.5f).toInt()
                end = (150 * scale + 0.5f).toInt()
                carbsParams.topMargin = top
                carbsParams.bottomMargin = bottom
                carbsParams.leftMargin = start
                carbsParams.rightMargin = end
            }
            3 -> {
                proteinGraph.progress = 40
                fatsGraph.progress = 75
                carbsGraph.progress = 100

                top = (0 * scale + 0.5f).toInt()
                bottom = (30 * scale + 0.5f).toInt()
                start = (155 * scale + 0.5f).toInt()
                end = (0 * scale + 0.5f).toInt()
                proteinParams.topMargin = top
                proteinParams.bottomMargin = bottom
                proteinParams.leftMargin = start
                proteinParams.rightMargin = end

                top = (130 * scale + 0.5f).toInt()
                bottom = (0 * scale + 0.5f).toInt()
                start = (0 * scale + 0.5f).toInt()
                end = (70 * scale + 0.5f).toInt()
                fatsParams.topMargin = top
                fatsParams.bottomMargin = bottom
                fatsParams.leftMargin = start
                fatsParams.rightMargin = end

                top = (0 * scale + 0.5f).toInt()
                bottom = (100 * scale + 0.5f).toInt()
                start = (0 * scale + 0.5f).toInt()
                end = (115 * scale + 0.5f).toInt()
                carbsParams.topMargin = top
                carbsParams.bottomMargin = bottom
                carbsParams.leftMargin = start
                carbsParams.rightMargin = end
            }
        }
    }
}