package com.example.dailyloss

import android.R.attr.data
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Main_Page : AppCompatActivity() {
    private lateinit var protein: TextView
    private lateinit var proteinProgressbar: ProgressBar
    private lateinit var carbs: TextView
    private lateinit var carbsProgressbar: ProgressBar
    private lateinit var fats: TextView
    private lateinit var fatsProgressbar: ProgressBar

    private var date: Date? = Calendar.getInstance().time
    private lateinit var calendar: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val name = findViewById<TextView>(R.id.main_nameValue)
        val subname = findViewById<TextView>(R.id.main_subnameValue)
        val DIof = findViewById<TextView>(R.id.mainPage_text9)

        protein = findViewById(R.id.mainPage_text13)
        proteinProgressbar = findViewById(R.id.mainPage_progressBar2)
        carbs = findViewById(R.id.mainPage_text16)
        carbsProgressbar = findViewById(R.id.mainPage_progressBar3)
        fats = findViewById(R.id.mainPage_text19)
        fatsProgressbar = findViewById(R.id.mainPage_progressBar4)

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

        calendar = Calendar.getInstance()
        updateSelectedDateText(date)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        ChangeCupOfWater(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10)

        name.text = sharedPreferences.getString("name_value", "")
        subname.text = sharedPreferences.getString("subname_value", "")
        DIof.text = (sharedPreferences.getInt("DIoF_value", 0)).toString()

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

    private fun updateSelectedDateText(date: Date?) {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val dateFormat = SimpleDateFormat("d_MMMM_yyyy", Locale.ENGLISH)
        val key = date?.let { dateFormat.format(it) } ?: "No date selected"

        val proteinMaxValue = (sharedPreferences.getInt("protein_g", 0)).toString()
        val proteinSetValue = sharedPreferences.getString(key, "0.0")
        val carbsMaxValue = (sharedPreferences.getInt("carbs_g", 0)).toString()
        val carbsSetValue = sharedPreferences.getString(key, "0.0")
        val fatsMaxValue = (sharedPreferences.getInt("fats_g", 0)).toString()
        val fatsSetValue = sharedPreferences.getString(key, "0.0")

        protein.text = proteinSetValue
        proteinProgressbar.progress = (proteinSetValue!!.toDouble() / proteinMaxValue.toDouble() * 100).toInt()
        carbs.text = carbsSetValue
        carbsProgressbar.progress = (carbsSetValue!!.toDouble() / carbsMaxValue.toDouble() * 100).toInt()
        fats.text = fatsSetValue
        fatsProgressbar.progress = (fatsSetValue!!.toDouble() / fatsMaxValue.toDouble() * 100).toInt()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {}
}