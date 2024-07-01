package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Stats_BMR : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_bmr)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val profile = Intent(this, Profile::class.java)
        startActivity(profile)
    }
}