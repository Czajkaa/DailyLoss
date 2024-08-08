package com.example.dailyloss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Food_Breakfast : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private val items = mutableListOf<List<String>>()
    private val colors = listOf(
        R.color.breakfast,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_breakfast)

        val recyclerView: RecyclerView = findViewById(R.id.breakfast_macro_recyclerView)
        adapter = MyAdapter(this, items, colors)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAdd: Button = findViewById(R.id.breakfast_macro_button)
        buttonAdd.setOnClickListener {
            val newItem = listOf(
                "Nowy widok " + (items.size + 1),
                "${(items.size + 1).toDouble()} g",
                "${(items.size + 1).toDouble()} g",
                "${(items.size + 1).toDouble()} g",
                "${(items.size + 1).toDouble()} g",
                "${(items.size + 1).toDouble()} g",
            )
            adapter.addItem(newItem)
        }
    }
}