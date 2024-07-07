package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Stats_BFP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_bfp)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Formulas

        val formula_1 = findViewById<Button>(R.id.bfp_formula_1)
        val formula_2 = findViewById<Button>(R.id.bfp_formula_2)

        formula_1.setOnClickListener {
            formula_1.isSelected = true
            formula_2.isSelected = false
            editor.putInt("BFP_formula", 0)
            editor.apply()
            updateValue()
        }
        formula_2.setOnClickListener {
            formula_1.isSelected = false
            formula_2.isSelected = true
            editor.putInt("BFP_formula", 1)
            editor.apply()
            updateValue()
        }
        updateValue()

        // Select formula

        when(sharedPreferences.getInt("BFP_formula", 0)) {
            0 -> formula_1.isSelected = true
            1 -> formula_2.isSelected = true
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val profile = Intent(this, Profile::class.java)
        startActivity(profile)
    }

    fun updateValue() {
        val text = findViewById<TextView>(R.id.bfp_value)
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val value = chceck_stats(sharedPreferences, "BFP")
        text.text = value
    }
}