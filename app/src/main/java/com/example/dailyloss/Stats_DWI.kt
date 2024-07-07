package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class Stats_DWI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_dwi)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Formulas

        val formula_1 = findViewById<Button>(R.id.dwi_formula_1)
        val formula_2 = findViewById<Button>(R.id.dwi_formula_2)
        val formula_3 = findViewById<Button>(R.id.dwi_formula_3)
        val formula_4 = findViewById<Button>(R.id.dwi_formula_4)

        formula_1.setOnClickListener {
            formula_1.isSelected = true
            formula_2.isSelected = false
            formula_3.isSelected = false
            formula_4.isSelected = false
            editor.putInt("DWI_formula", 0)
            editor.apply()
            updateValue()
        }
        formula_2.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = true
            formula_3.isSelected = false
            formula_4.isSelected = false
            editor.putInt("DWI_formula", 1)
            editor.apply()
            updateValue()
        }
        formula_3.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = false
            formula_3.isSelected = true
            formula_4.isSelected = false
            editor.putInt("DWI_formula", 2)
            editor.apply()
            updateValue()
        }
        formula_4.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = false
            formula_3.isSelected = false
            formula_4.isSelected = true
            editor.putInt("DWI_formula", 3)
            editor.apply()
            updateValue()
        }
        updateValue()

        // Select formula

        when(sharedPreferences.getInt("DWI_formula", 0)) {
            0 -> formula_1.isSelected = true
            1 -> formula_2.isSelected = true
            2 -> formula_3.isSelected = true
            3 -> formula_4.isSelected = true
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val profile = Intent(this, Profile::class.java)
        startActivity(profile)
    }

    fun updateValue() {
        val text = findViewById<TextView>(R.id.dwi_value)
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val value = chceck_stats(sharedPreferences, "DWI")
        text.text = value
    }
}