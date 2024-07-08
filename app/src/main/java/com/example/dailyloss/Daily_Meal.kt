package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class Daily_Meal : AppCompatActivity() {
    private lateinit var calendar: Calendar
    private lateinit var recyclerView: RecyclerView
    private lateinit var monthYearText: TextView
    private lateinit var kcal: TextView
    private lateinit var protein: TextView
    private lateinit var carbs: TextView
    private lateinit var fats: TextView
    private lateinit var adapter: DayAdapter
    private var selectedDate: Date? = Calendar.getInstance().time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_meal)

        calendar = Calendar.getInstance()

        monthYearText = findViewById(R.id.monthYearText)
        kcal = findViewById(R.id.daily_meal_macroValue)
        protein = findViewById(R.id.daily_meal_proteinValue)
        carbs = findViewById(R.id.daily_meal_carbsValue)
        fats = findViewById(R.id.daily_meal_fatsValue)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val days = getDaysOfWeek()
        adapter = DayAdapter(days, selectedDate) { date ->
            selectedDate = date
            saveTextForSelectedDate(date)
            updateSelectedDateText(date)
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
    }

    private fun getDaysOfWeek(): List<Day> {
        val days = mutableListOf<Day>()
        val dayNames = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        val startOfWeek = calendar.clone() as Calendar
        startOfWeek.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)

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
        val carbsMaxValue = (sharedPreferences.getInt("carbs_g", 0)).toString()
        val fatsMaxValue = (sharedPreferences.getInt("fats_g", 0)).toString()

        protein.text = sharedPreferences.getString(key, "0.0") + "/" + proteinMaxValue + " g"
        carbs.text = sharedPreferences.getString(key, "0.0") + "/" + carbsMaxValue + " g"
        fats.text = sharedPreferences.getString(key, "0.0") + "/" + fatsMaxValue + " g"
    }

    private fun saveTextForSelectedDate(date: Date?) {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dateFormat = SimpleDateFormat("d_MMMM_yyyy", Locale.ENGLISH) // 8_July_2024
        val key = date?.let { dateFormat.format(it) } ?: "No date selected"
        when(key) {
            "8_July_2024" -> editor.putString(key, "8.0")
            "9_July_2024" -> editor.putString(key, "9.0")
            "10_July_2024" -> editor.putString(key, "10.0")
            "11_July_2024" -> editor.putString(key, "11.0")
        }
        editor.apply()
    }
}