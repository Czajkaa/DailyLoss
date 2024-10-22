package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Search_Product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        val recyclerView: RecyclerView = findViewById(R.id.search_product_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

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

            val adapter = ProductAdapter(imageList, textList)
            recyclerView.adapter = adapter
        }
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
        val dailyMeal = Intent(this, Daily_Meal::class.java)
        startActivity(dailyMeal)
    }
}