package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class Food_Snack : AppCompatActivity() {
    private var sum_calories = 0.0
    private var sum_protein = 0.0
    private var sum_carbs = 0.0
    private var sum_fats = 0.0
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var productList: MutableList<String>
    private lateinit var adapter: MealAdapter
    private val items = mutableListOf<List<String>>()
    private val colors = listOf(
        R.color.snack,
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_snack)

        val buttonAdd: Button = findViewById(R.id.snack_macro_button)
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val product = Intent(this, Search_Product::class.java)
        buttonAdd.setOnClickListener {
            editor.putString("selected_time_to_eat", "Snack")
            editor.apply()
            startActivity(product)
        }

        val selectedDate = sharedPreferences.getString("selected_date", "")
        val context = this
        if (SharedPreferenceHelper2.isListExists(context)) {
            productList = SharedPreferenceHelper2.getList(context).toMutableList()
            for (i in 1..productList.size) {
                val input = productList[i - 1]
                val check = input.substring(0, 14)  // Set number for time to it (09_09_24_Snack -> 14)
                if(selectedDate + "_" + "Snack" == check) {
                    newProductView (input)
                }
            }
        }

        val allCalorie_value = findViewById<TextView>(R.id.snack_macro_macroValue)
        val allProtein_value = findViewById<TextView>(R.id.snack_macro_proteinValue)
        val allCarbs_value = findViewById<TextView>(R.id.snack_macro_carbsValue)
        val allFats_value = findViewById<TextView>(R.id.snack_macro_fatsValue)
        val allProtein_progress = findViewById<ProgressBar>(R.id.snack_macro_protein_progressBar)
        val allCarbs_progress = findViewById<ProgressBar>(R.id.snack_macro_carbs_progressBar)
        val allFats_progress = findViewById<ProgressBar>(R.id.snack_macro_fats_progressBar)

        val maxProtein = sharedPreferences.getInt("protein_g", 0).toDouble() / 4.0
        val maxCarbs = sharedPreferences.getInt("carbs_g", 0).toDouble() / 4.0
        val maxFats = sharedPreferences.getInt("fats_g", 0).toDouble() / 4.0

        allCalorie_value.text = String.format(Locale.US, "%.1f", sum_calories)
        allProtein_value.text = String.format(Locale.US, "%.1f", sum_protein) + "/" + String.format(Locale.US, "%.1f", maxProtein) + " g"
        allCarbs_value.text = String.format(Locale.US, "%.1f", sum_carbs) + "/" + String.format(Locale.US, "%.1f", maxCarbs) + " g"
        allFats_value.text = String.format(Locale.US, "%.1f", sum_fats) + "/" + String.format(Locale.US, "%.1f", maxFats) + " g"

        allProtein_progress.progress = (sum_protein / maxProtein * 100).toInt()
        allCarbs_progress.progress = (sum_carbs / maxCarbs * 100).toInt()
        allFats_progress.progress = (sum_fats / maxFats * 100).toInt()

        editor.putString(selectedDate + "Snack" + "DayCalories", String.format(Locale.US, "%.1f", sum_calories))
        editor.putString(selectedDate + "Snack" + "DayProtein", String.format(Locale.US, "%.1f", sum_protein))
        editor.putString(selectedDate + "Snack" + "DayCarbs", String.format(Locale.US, "%.1f", sum_carbs))
        editor.putString(selectedDate + "Snack" + "DayFats", String.format(Locale.US, "%.1f", sum_fats))
        editor.apply()
    }

    private fun newProductView (key: String) {
        val recyclerView: RecyclerView = findViewById(R.id.snack_macro_recyclerView)
        adapter = MealAdapter(this, items, colors, sharedPreferences)
        recyclerView.layoutManager = LinearLayoutManager(this)

        var string = key + "_" + "Name"
        val name = sharedPreferences.getString(string, "").toString()
        string = key + "_" + "Amount"
        val amount = sharedPreferences.getString(string, "").toString()
        string = key + "_" + "Calories"
        val calories = sharedPreferences.getString(string, "").toString()
        string = key + "_" + "Protein"
        val protein = sharedPreferences.getString(string, "").toString()
        string = key + "_" + "Carbs"
        val carbs = sharedPreferences.getString(string, "").toString()
        string = key + "_" + "Fats"
        val fats = sharedPreferences.getString(string, "").toString()

        sum_calories += calories.replace(",", ".").toDouble()
        sum_protein += protein.replace(",", ".").toDouble()
        sum_carbs += carbs.replace(",", ".").toDouble()
        sum_fats += fats.replace(",", ".").toDouble()

        val newItem = listOf(
            name,
            "$amount g",
            "$calories kcal",
            "$protein g",
            "$carbs g",
            "$fats g",
            key
        )
        adapter.addItem(newItem)
        recyclerView.adapter = adapter
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val page = Intent(this, Daily_Meal::class.java)
        startActivity(page)
    }
}