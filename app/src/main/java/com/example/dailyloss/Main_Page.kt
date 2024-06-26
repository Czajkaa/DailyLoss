package com.example.dailyloss

import android.R.attr.data
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val user = findViewById<ImageButton>(R.id.mainPage_imageButton2)
        val card1 = findViewById<ImageButton>(R.id.mainPage_card1_image)
        val card2 = findViewById<ImageButton>(R.id.mainPage_card2_image)
        val card3 = findViewById<ImageButton>(R.id.mainPage_card3_image)
        val card4 = findViewById<ImageButton>(R.id.mainPage_card4_image)
        val card5 = findViewById<ImageButton>(R.id.mainPage_card5_image)
        val card6 = findViewById<ImageButton>(R.id.mainPage_card6_image)
        val card7 = findViewById<ImageButton>(R.id.mainPage_card7_image)
        val card8 = findViewById<ImageButton>(R.id.mainPage_card8_image)
        val card9 = findViewById<ImageButton>(R.id.mainPage_card9_image)
        val card10 = findViewById<ImageButton>(R.id.mainPage_card10_image)

        ChangeCupOfWater(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10)

        val profile = Intent(this, Profile::class.java)
        user.setOnClickListener {
            startActivity(profile)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {}
}