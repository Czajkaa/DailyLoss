package com.example.dailyloss

import android.R.attr.data
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val name = findViewById<TextView>(R.id.main_nameValue)
        val subname = findViewById<TextView>(R.id.main_subnameValue)

        val DIof = findViewById<TextView>(R.id.mainPage_text9)
        val protein = findViewById<TextView>(R.id.mainPage_text13)
        val carbs = findViewById<TextView>(R.id.mainPage_text16)
        val fats = findViewById<TextView>(R.id.mainPage_text19)
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

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        ChangeCupOfWater(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10)

        name.text = sharedPreferences.getString("name_value", "Name")
        subname.text = sharedPreferences.getString("subname_value", "Subname")
        DIof.text = (sharedPreferences.getInt("DIoF_value", 0)).toString()
        protein.text = (sharedPreferences.getInt("protein_g", 0)).toString()
        carbs.text = (sharedPreferences.getInt("carbs_g", 0)).toString()
        fats.text = (sharedPreferences.getInt("fats_g", 0)).toString()

        val user = findViewById<ImageButton>(R.id.main_profileButton)
        val profile = Intent(this, Profile::class.java)
        user.setOnClickListener {
            startActivity(profile)
        }
        val add_meal = findViewById<Button>(R.id.mainPage_imageButton3)
        val daily_meal = Intent(this, Daily_Meal::class.java)
        add_meal.setOnClickListener {
            startActivity(daily_meal)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {}
}