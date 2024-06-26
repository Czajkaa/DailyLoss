package com.example.dailyloss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val activity = findViewById<Spinner>(R.id.spinner)
        val goal = findViewById<Spinner>(R.id.spinner2)
        ArrayAdapter.createFromResource(
            this,
            R.array.activity,
            R.layout.spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            activity.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.goal,
            R.layout.spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            goal.adapter = adapter
        }
    }
}