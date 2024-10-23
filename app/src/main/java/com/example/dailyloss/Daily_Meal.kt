package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Daily_Meal : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var calendar: Calendar
    private lateinit var recyclerView: RecyclerView
    private lateinit var monthYearText: TextView
    private lateinit var kcal: TextView
    private lateinit var protein: TextView
    private lateinit var proteinProgressbar: ProgressBar
    private lateinit var carbs: TextView
    private lateinit var carbsProgressbar: ProgressBar
    private lateinit var fats: TextView
    private lateinit var fatsProgressbar: ProgressBar
    private lateinit var adapter: DayAdapter
    private lateinit var breakfastButton: Button
    private lateinit var lunchButton: Button
    private lateinit var snackButton: Button
    private lateinit var dinnerButton: Button
    private lateinit var addProductButton: Button

    private var selectedDate: Date? = Calendar.getInstance().time

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_meal)

        calendar = Calendar.getInstance()
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        monthYearText = findViewById(R.id.monthYearText)
        kcal = findViewById(R.id.daily_meal_macroValue)
        protein = findViewById(R.id.daily_meal_proteinValue)
        proteinProgressbar = findViewById(R.id.daily_meal_protein_progressBar)
        carbs = findViewById(R.id.daily_meal_carbsValue)
        carbsProgressbar = findViewById(R.id.daily_meal_carbs_progressBar)
        fats = findViewById(R.id.daily_meal_fatsValue)
        fatsProgressbar = findViewById(R.id.daily_meal_fats_progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val days = getDaysOfWeek()
        adapter = DayAdapter(days, selectedDate) { date ->
            selectedDate = date
            saveTextForSelectedDate(date)
            updateSelectedDateText(date)
            val dateFormat = SimpleDateFormat("dd_MM_yy", Locale.ENGLISH) // 08_05_24
            val day_key = selectedDate?.let { dateFormat.format(it) }
            updateMealValues(day_key)
        }
        recyclerView.adapter = adapter

        setMonthYearText()
        saveTextForSelectedDate(selectedDate)
        updateSelectedDateText(selectedDate)

        val prevWeekButton: ImageButton = findViewById(R.id.prevWeekButton)
        val nextWeekButton: ImageButton = findViewById(R.id.nextWeekButton)

        prevWeekButton.setOnClickListener {
            saveTextForSelectedDate(selectedDate)
            calendar.add(Calendar.WEEK_OF_YEAR, -1)
            updateWeekView()
        }

        nextWeekButton.setOnClickListener {
            saveTextForSelectedDate(selectedDate)
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
            updateWeekView()
        }

        breakfastButton = findViewById(R.id.daily_meal_breakfastButton)
        lunchButton = findViewById(R.id.daily_meal_lunchButton)
        snackButton = findViewById(R.id.daily_meal_snackButton)
        dinnerButton = findViewById(R.id.daily_meal_dinnerButton)
        addProductButton = findViewById(R.id.daily_meal_add_mealButton)

        val dateFormat = SimpleDateFormat("dd_MM_yy", Locale.ENGLISH) // 08_05_24
        val day_key = selectedDate?.let { dateFormat.format(it) }
        updateMealValues(day_key)

        val breakfastPage = Intent(this, Food_Breakfast::class.java)
        breakfastButton.setOnClickListener {
            val key = selectedDate?.let { dateFormat.format(it) }
            editor.putString("selected_date", key)
            editor.apply()

            startActivity(breakfastPage)
        }
        val lunchPage = Intent(this, Food_Lunch::class.java)
        lunchButton.setOnClickListener {
            val key = selectedDate?.let { dateFormat.format(it) }
            editor.putString("selected_date", key)
            editor.apply()

            startActivity(lunchPage)
        }
        val snackPage = Intent(this, Food_Snack::class.java)
        snackButton.setOnClickListener {
            val key = selectedDate?.let { dateFormat.format(it) }
            editor.putString("selected_date", key)
            editor.apply()

            startActivity(snackPage)
        }
        val dinnerPage = Intent(this, Food_Dinner::class.java)
        dinnerButton.setOnClickListener {
            val key = selectedDate?.let { dateFormat.format(it) }
            editor.putString("selected_date", key)
            editor.apply()

            startActivity(dinnerPage)
        }
        val addProductPage = Intent(this, Add_Product::class.java)
        addProductButton.setOnClickListener {
            startActivity(addProductPage)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateMealValues (day_key: String?) {
        val breakfast_calories = findViewById<TextView>(R.id.daily_meal_breakfastKcal)
        val breakfast_protein = findViewById<TextView>(R.id.daily_meal_breakfastProteinUint)
        val breakfast_carbs = findViewById<TextView>(R.id.daily_meal_breakfastCarbsUint)
        val breakfast_fats = findViewById<TextView>(R.id.daily_meal_breakfastFatsUint)

        val lunch_calories = findViewById<TextView>(R.id.daily_meal_lunchKcal)
        val lunch_protein = findViewById<TextView>(R.id.daily_meal_lunchProteinUint)
        val lunch_carbs = findViewById<TextView>(R.id.daily_meal_lunchCarbsUint)
        val lunch_fats = findViewById<TextView>(R.id.daily_meal_lunchFatsUint)

        val snack_calories = findViewById<TextView>(R.id.daily_meal_snackKcal)
        val snack_protein = findViewById<TextView>(R.id.daily_meal_snackProteinUint)
        val snack_carbs = findViewById<TextView>(R.id.daily_meal_snackCarbsUint)
        val snack_fats = findViewById<TextView>(R.id.daily_meal_snackFatsUint)

        val dinner_calories = findViewById<TextView>(R.id.daily_meal_dinnerKcal)
        val dinner_protein = findViewById<TextView>(R.id.daily_meal_dinnerProteinUint)
        val dinner_carbs = findViewById<TextView>(R.id.daily_meal_dinnerCarbsUint)
        val dinner_fats = findViewById<TextView>(R.id.daily_meal_dinnerFatsUint)

        var key = day_key + "Breakfast" + "DayCalories"
        var value = sharedPreferences.getString(key, "0.0")
        breakfast_calories.text = "$value kcal"
        key = day_key + "Breakfast" + "DayProtein"
        value = sharedPreferences.getString(key, "0.0")
        breakfast_protein.text = "$value kcal"
        key = day_key + "Breakfast" + "DayCarbs"
        value = sharedPreferences.getString(key, "0.0")
        breakfast_carbs.text = "$value kcal"
        key = day_key + "Breakfast" + "DayFats"
        value = sharedPreferences.getString(key, "0.0")
        breakfast_fats.text = "$value kcal"

        key = day_key + "Lunch" + "DayCalories"
        value = sharedPreferences.getString(key, "0.0")
        lunch_calories.text = "$value kcal"
        key = day_key + "Lunch" + "DayProtein"
        value = sharedPreferences.getString(key, "0.0")
        lunch_protein.text = "$value kcal"
        key = day_key + "Lunch" + "DayCarbs"
        value = sharedPreferences.getString(key, "0.0")
        lunch_carbs.text = "$value kcal"
        key = day_key + "Lunch" + "DayFats"
        value = sharedPreferences.getString(key, "0.0")
        lunch_fats.text = "$value kcal"

        key = day_key + "Snack" + "DayCalories"
        value = sharedPreferences.getString(key, "0.0")
        snack_calories.text = "$value kcal"
        key = day_key + "Snack" + "DayProtein"
        value = sharedPreferences.getString(key, "0.0")
        snack_protein.text = "$value kcal"
        key = day_key + "Snack" + "DayCarbs"
        value = sharedPreferences.getString(key, "0.0")
        snack_carbs.text = "$value kcal"
        key = day_key + "Snack" + "DayFats"
        value = sharedPreferences.getString(key, "0.0")
        snack_fats.text = "$value kcal"

        key = day_key + "Dinner" + "DayCalories"
        value = sharedPreferences.getString(key, "0.0")
        dinner_calories.text = "$value kcal"
        key = day_key + "Dinner" + "DayProtein"
        value = sharedPreferences.getString(key, "0.0")
        dinner_protein.text = "$value kcal"
        key = day_key + "Dinner" + "DayCarbs"
        value = sharedPreferences.getString(key, "0.0")
        dinner_carbs.text = "$value kcal"
        key = day_key + "Dinner" + "DayFats"
        value = sharedPreferences.getString(key, "0.0")
        dinner_fats.text = "$value kcal"
    }

    private fun getDaysOfWeek(): List<Day> {
        val days = mutableListOf<Day>()
        val dayNames = arrayOf("Thu", "Mon", "Fri", "Tue", "Sat", "Wed", "Sun")

        val startOfWeek = calendar.clone() as Calendar
        startOfWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        for (i in 0..6) {
            val dayOfWeek = (startOfWeek.get(Calendar.DAY_OF_WEEK) + i - 1) % 7
            val dayNumber = startOfWeek.get(Calendar.DAY_OF_MONTH)
            days.add(Day(dayNames[dayOfWeek], dayNumber, startOfWeek.time))
            startOfWeek.add(Calendar.DAY_OF_MONTH, 1)
        }

        return days
    }

    private fun setMonthYearText() {
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH) // Ustawienie Locale na ENGLISH
        monthYearText.text = dateFormat.format(calendar.time)
    }

    private fun updateWeekView() {
        val days = getDaysOfWeek()
        adapter.updateDays(days, selectedDate)
        setMonthYearText()
        updateSelectedDateText(selectedDate)
    }

    @SuppressLint("SetTextI18n")
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

        protein.text = "$proteinSetValue/$proteinMaxValue g"
        proteinProgressbar.progress = (proteinSetValue!!.toDouble() / proteinMaxValue.toDouble() * 100).toInt()
        carbs.text = "$carbsSetValue/$carbsMaxValue g"
        carbsProgressbar.progress = (carbsSetValue!!.toDouble() / carbsMaxValue.toDouble() * 100).toInt()
        fats.text = "$fatsSetValue/$fatsMaxValue g"
        fatsProgressbar.progress = (fatsSetValue!!.toDouble() / fatsMaxValue.toDouble() * 100).toInt()
    }

    private fun saveTextForSelectedDate(date: Date?) {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dateFormat = SimpleDateFormat("d_MMMM_yyyy", Locale.ENGLISH) // 8_July_2024
        val key = date?.let { dateFormat.format(it) } ?: "No date selected"
        when(key) {
            "8_July_2024" -> editor.putString(key, "80.0")
            "9_July_2024" -> editor.putString(key, "90.0")
            "10_July_2024" -> editor.putString(key, "100.0")
            "27_September_2024" -> editor.putString(key, "110.0")
        }
        editor.apply()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val mainPage = Intent(this, Main_Page::class.java)
        startActivity(mainPage)
    }
}