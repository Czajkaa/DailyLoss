package com.example.dailyloss

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Food_Dinner : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var productList: MutableList<String>
    private lateinit var adapter: MealAdapter
    private val items = mutableListOf<List<String>>()
    private val colors = listOf(
        R.color.dinner,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_dinner)

        val buttonAdd: Button = findViewById(R.id.dinner_macro_button)
        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val product = Intent(this, Search_Product::class.java)
        buttonAdd.setOnClickListener {
            editor.putString("selected_time_to_eat", "Dinner")
            editor.apply()
            startActivity(product)
        }

        val selectedDate = sharedPreferences.getString("selected_date", "")
        val context = this
        if (SharedPreferenceHelper2.isListExists(context)) {
            productList = SharedPreferenceHelper2.getList(context).toMutableList()
            for (i in 1..productList.size) {
                val input = productList[i - 1]
                val check = input.substring(0, 15)  // Set number for time to it (09_09_24_Dinner -> 15)
                if(selectedDate + "_" + "Dinner" == check) {
                    newProductView (input)
                }
            }
        }
    }

    private fun newProductView (key: String) {
        val recyclerView: RecyclerView = findViewById(R.id.dinner_macro_recyclerView)
        adapter = MealAdapter(this, items, colors, sharedPreferences, key)
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

        val newItem = listOf(
            name,
            "$amount g",
            "$calories g",
            "$protein g",
            "$carbs g",
            "$fats g",
        )
        adapter.addItem(newItem)
        recyclerView.adapter = adapter
    }
}