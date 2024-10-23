package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Search_Product : AppCompatActivity() {
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        val recyclerView: RecyclerView = findViewById(R.id.search_product_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val filter: EditText = findViewById(R.id.search_product_nameValue)
        val button = findViewById<Button>(R.id.search_product_add)
        val addProductPage = Intent(this, Add_Product::class.java)
        button.setOnClickListener {
            startActivity(addProductPage)
        }


        val imageList: MutableList<Bitmap> = mutableListOf()
        val textList: MutableList<ProductData> = mutableListOf()

        val context = this
        val codeList: MutableList<String>
        if (SharedPreferenceHelper.isListExists(context)) {
            codeList = SharedPreferenceHelper.getList(context).toMutableList()
            for (i in 1..codeList.size) {
                readData(codeList[i-1], imageList, textList)
            }

            adapter = ProductAdapter(imageList, textList)
            recyclerView.adapter = adapter
        }

        filter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString(), textList, imageList)
            }
        })
    }

    private fun filterList(query: String, fullItemList: MutableList<ProductData>, fullBitmapList: MutableList<Bitmap>) {
        val filteredItemList = fullItemList.filter {
            it.text1.contains(query, ignoreCase = true) || it.text2.contains(query, ignoreCase = true)
        }

        val filteredBitmapList = filteredItemList.map {
            val index = fullItemList.indexOf(it)
            fullBitmapList[index]
        }

        // Zaktualizuj dane w adapterze
        adapter.updateData(filteredItemList, filteredBitmapList)
    }

    private fun readData(code: String, imageList: MutableList<Bitmap>, textList: MutableList<ProductData>) {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        var key = "${code}Name"
        val name = sharedPreferences.getString(key, "").toString()
        key = "${code}Image"
        val loadedImage = sharedPreferences.getString(key, "").toString()
        val byteArray = Base64.decode(loadedImage, Base64.DEFAULT)
        val image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        key = "${code}Brand"
        val brand = sharedPreferences.getString(key, "").toString()
        key = "${code}Calories"
        val calories = sharedPreferences.getString(key, "0").toString()

        imageList.add(image)
        textList.add(ProductData(name, brand, calories))
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val main = Intent(this, Daily_Meal::class.java)
        val breakfast = Intent(this, Food_Breakfast::class.java)
        val lunch = Intent(this, Food_Lunch::class.java)
        val snack = Intent(this, Food_Snack::class.java)
        val dinner = Intent(this, Food_Dinner::class.java)
        val time_day = sharedPreferences.getString("selected_time_to_eat", "")
        when (time_day) {
            "Breakfast" -> startActivity(breakfast)
            "Lunch" -> startActivity(lunch)
            "Snack" -> startActivity(snack)
            "Dinner" -> startActivity(dinner)
            else -> startActivity(main)
        }
    }
}