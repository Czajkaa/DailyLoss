package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Search_Product_Check : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var name: String
    private lateinit var brand: String
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var codeList: MutableList<String>
    private var servingAmountValue: Double = 0.0

    private lateinit var imageView: ImageView
    private lateinit var caloriesView: TextView
    private var calories: Double = 0.0
    private lateinit var fatView: TextView
    private var fat: Double = 0.0
    private lateinit var fat1View: TextView
    private var fat1: Double = 0.0
    private lateinit var fat2View: TextView
    private var fat2: Double = 0.0
    private lateinit var fat3View: TextView
    private var fat3: Double = 0.0
    private lateinit var fat4View: TextView
    private var fat4: Double = 0.0
    private lateinit var proteinView: TextView
    private var protein: Double = 0.0
    private lateinit var carbsView: TextView
    private var carbs: Double = 0.0
    private lateinit var carbs1View: TextView
    private var carbs1: Double = 0.0
    private lateinit var carbs2View: TextView
    private var carbs2: Double = 0.0
    private lateinit var vitamin1View: TextView
    private var vitamin1: Double = 0.0
    private lateinit var vitamin2View: TextView
    private var vitamin2: Double = 0.0
    private lateinit var vitamin3View: TextView
    private var vitamin3: Double = 0.0
    private lateinit var vitamin4View: TextView
    private var vitamin4: Double = 0.0
    private lateinit var vitamin5View: TextView
    private var vitamin5: Double = 0.0
    private lateinit var vitamin6View: TextView
    private var vitamin6: Double = 0.0
    private lateinit var vitamin7View: TextView
    private var vitamin7: Double = 0.0
    private lateinit var mineral1View: TextView
    private var mineral1: Double = 0.0
    private lateinit var mineral2View: TextView
    private var mineral2: Double = 0.0
    private lateinit var mineral3View: TextView
    private var mineral3: Double = 0.0
    private lateinit var mineral4View: TextView
    private var mineral4: Double = 0.0
    private lateinit var mineral5View: TextView
    private var mineral5: Double = 0.0
    private lateinit var mineral6View: TextView
    private var mineral6: Double = 0.0
    private lateinit var mineral7View: TextView
    private var mineral7: Double = 0.0
    private lateinit var mineral8View: TextView
    private var mineral8: Double = 0.0
    private lateinit var mineral9View: TextView
    private var mineral9: Double = 0.0
    private lateinit var mineral10View: TextView
    private var mineral10: Double = 0.0
    private lateinit var other1View: TextView
    private var other1: Double = 0.0
    private lateinit var other2View: TextView
    private var other2: Double = 0.0
    private lateinit var other3View: TextView
    private var other3: Double = 0.0
    private lateinit var other4View: TextView
    private var other4: Double = 0.0
    private lateinit var deleteButton: Button

    @SuppressLint("CommitPrefEdits", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product_check)

        name = intent.getStringExtra("text1").toString()
        brand = intent.getStringExtra("text2").toString()

        sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val nameView: TextView = findViewById(R.id.search_product_check_name)
        imageView = findViewById(R.id.search_product_check_image)
        val brandView: TextView = findViewById(R.id.search_product_check_grams)
        caloriesView = findViewById(R.id.search_product_check_caloriesValue)
        fatView = findViewById(R.id.search_product_check_fatValue)
        fat1View = findViewById(R.id.search_product_check_fat_1_Value)
        fat2View = findViewById(R.id.search_product_check_fat_2_Value)
        fat3View = findViewById(R.id.search_product_check_fat_3_Value)
        fat4View = findViewById(R.id.search_product_check_fat_4_Value)
        proteinView = findViewById(R.id.search_product_check_proteinValue)
        carbsView = findViewById(R.id.search_product_check_carbsValue)
        carbs1View = findViewById(R.id.search_product_check_carbs_1_Value)
        carbs2View = findViewById(R.id.search_product_check_carbs_2_Value)
        vitamin1View = findViewById(R.id.search_product_check_vitamin_1_Value)
        vitamin2View = findViewById(R.id.search_product_check_vitamin_2_Value)
        vitamin3View = findViewById(R.id.search_product_check_vitamin_3_Value)
        vitamin4View = findViewById(R.id.search_product_check_vitamin_4_Value)
        vitamin5View = findViewById(R.id.search_product_check_vitamin_5_Value)
        vitamin6View = findViewById(R.id.search_product_check_vitamin_6_Value)
        vitamin7View = findViewById(R.id.search_product_check_vitamin_7_Value)
        mineral1View = findViewById(R.id.search_product_check_mineral_1_Value)
        mineral2View = findViewById(R.id.search_product_check_mineral_2_Value)
        mineral3View = findViewById(R.id.search_product_check_mineral_3_Value)
        mineral4View = findViewById(R.id.search_product_check_mineral_4_Value)
        mineral5View = findViewById(R.id.search_product_check_mineral_5_Value)
        mineral6View = findViewById(R.id.search_product_check_mineral_6_Value)
        mineral7View = findViewById(R.id.search_product_check_mineral_7_Value)
        mineral8View = findViewById(R.id.search_product_check_mineral_8_Value)
        mineral9View = findViewById(R.id.search_product_check_mineral_9_Value)
        mineral10View = findViewById(R.id.search_product_check_mineral_10_Value)
        other1View = findViewById(R.id.search_product_check_other_1_Value)
        other2View = findViewById(R.id.search_product_check_other_2_Value)
        other3View = findViewById(R.id.search_product_check_other_3_Value)
        other4View = findViewById(R.id.search_product_check_other_4_Value)
        deleteButton = findViewById(R.id.search_product_check_deleteButton)
        val addButton = findViewById<Button>(R.id.search_product_check_create)
        val servingAmount: EditText = findViewById(R.id.search_product_check_servingAmount)

        context = this
        codeList = SharedPreferenceHelper.getList(context).toMutableList()

        nameView.text = name
        brandView.text = brand
        val code = brand + name
        deleteButton.setOnClickListener {
            deleteData(code)
        }

        addButton.setOnClickListener {
            saveData()
        }
        updateAmount(100.0)
        servingAmount.onTextChanged { newText ->
            if (newText == null || newText == "") updateAmount(0.0)
            else updateAmount(newText.toDouble())
            addButton.isEnabled = servingAmountValue != 0.0
        }
    }

    @SuppressLint("DefaultLocale")
    private fun saveData() {
        val selectedDate = sharedPreferences.getString("selected_date", "")
        val selected_time_to_eat = sharedPreferences.getString("selected_time_to_eat", "")
        val editor = sharedPreferences.edit()

        val selectedList: MutableList<String> = if (SharedPreferenceHelper2.isListExists(context)) {
            SharedPreferenceHelper2.getList(context).toMutableList()
        } else {
            mutableListOf()
        }

        var string = ""
        val code2 = selectedDate + "_" + selected_time_to_eat + "_" + brand + "_" + name
        if (!selectedList.contains(code2)) {
            selectedList.add(code2)
            SharedPreferenceHelper2.saveList(context, selectedList)

            // Name
            string = code2 + "_" + "Name"
            editor.putString(string, name)
            // Amount
            string = code2 + "_" + "Amount"
            editor.putString(string, String.format("%.1f", servingAmountValue))
            // Calories
            string = code2 + "_" + "Calories"
            editor.putString(string, String.format("%.1f", calories))
            // Protein
            string = code2 + "_" + "Protein"
            editor.putString(string, String.format("%.1f", protein))
            // Carbs
            string = code2 + "_" + "Carbs"
            editor.putString(string, String.format("%.1f", carbs))
            // Fats
            string = code2 + "_" + "Fats"
            editor.putString(string, String.format("%.1f", fat))
        }
        else {
            // Amount
            string = code2 + "_" + "Amount"
            var value1 = sharedPreferences.getString(string, "0.0")?.trim()?.replace(",", ".")?.toDouble()
            editor.putString(string, String.format("%.1f", servingAmountValue + value1!!))
            // Calories
            string = code2 + "_" + "Calories"
            value1 = sharedPreferences.getString(string, "0.0")?.trim()?.replace(",", ".")?.toDouble()
            editor.putString(string, String.format("%.1f", calories + value1!!))
            // Protein
            string = code2 + "_" + "Protein"
            value1 = sharedPreferences.getString(string, "0.0")?.trim()?.replace(",", ".")?.toDouble()
            editor.putString(string, String.format("%.1f", protein + value1!!))
            // Carbs
            string = code2 + "_" + "Carbs"
            value1 = sharedPreferences.getString(string, "0.0")?.trim()?.replace(",", ".")?.toDouble()
            editor.putString(string, String.format("%.1f", carbs + value1!!))
            // Fats
            string = code2 + "_" + "Fats"
            value1 = sharedPreferences.getString(string, "0.0")?.trim()?.replace(",", ".")?.toDouble()
            editor.putString(string, String.format("%.1f", fat + value1!!))
        }

        Toast.makeText(this, R.string.add_product_page_47, Toast.LENGTH_SHORT).show()
        editor.apply()

    }

    @SuppressLint("DefaultLocale")
    private fun updateAmount(servingAmount: Double) {
        val code = brand + name
        servingAmountValue = servingAmount
        for (i in 1..codeList.size) {
            if(code == codeList[i-1]) {
                val type = readData2(codeList[i-1], "type")
                val image = readData(codeList[i-1])
                imageView.setImageBitmap(image)

                if (type == "Liquid") {
                    val liquid = readData2(codeList[i-1], "liquid").toDouble()
                    servingAmountValue = (100 / liquid) * servingAmount
                }

                calories = ((readData2(codeList[i-1], "calories").toDouble()) * servingAmountValue / 100.0)
                caloriesView.text = String.format("%.1f", calories)
                fat = ((readData2(codeList[i-1], "fat").toDouble()) * servingAmountValue / 100.0)
                fatView.text = String.format("%.1f", fat)
                fat1 = ((readData2(codeList[i-1], "fat1").toDouble()) * servingAmountValue / 100.0)
                fat1View.text = String.format("%.1f", fat1)
                fat2 = ((readData2(codeList[i-1], "fat2").toDouble()) * servingAmountValue / 100.0)
                fat2View.text = String.format("%.1f", fat2)
                fat3 = ((readData2(codeList[i-1], "fat3").toDouble()) * servingAmountValue / 100.0)
                fat3View.text = String.format("%.1f", fat3)
                fat4 = ((readData2(codeList[i-1], "fat4").toDouble()) * servingAmountValue / 100.0)
                fat4View.text = String.format("%.1f", fat4)
                protein = ((readData2(codeList[i-1], "protein").toDouble()) * servingAmountValue / 100.0)
                proteinView.text = String.format("%.1f", protein)
                carbs = ((readData2(codeList[i-1], "carbs").toDouble()) * servingAmountValue / 100.0)
                carbsView.text = String.format("%.1f", carbs)
                carbs1 = ((readData2(codeList[i-1], "carbs1").toDouble()) * servingAmountValue / 100.0)
                carbs1View.text = String.format("%.1f", carbs1)
                carbs2 = ((readData2(codeList[i-1], "carbs2").toDouble()) * servingAmountValue / 100.0)
                carbs2View.text = String.format("%.1f", carbs2)
                vitamin1 = ((readData2(codeList[i-1], "vitamin1").toDouble()) * servingAmountValue / 100.0)
                vitamin1View.text = String.format("%.1f", vitamin1)
                vitamin2 = ((readData2(codeList[i-1], "vitamin2").toDouble()) * servingAmountValue / 100.0)
                vitamin2View.text = String.format("%.1f", vitamin2)
                vitamin3 = ((readData2(codeList[i-1], "vitamin3").toDouble()) * servingAmountValue / 100.0)
                vitamin3View.text = String.format("%.1f", vitamin3)
                vitamin4 = ((readData2(codeList[i-1], "vitamin4").toDouble()) * servingAmountValue / 100.0)
                vitamin4View.text = String.format("%.1f", vitamin4)
                vitamin5 = ((readData2(codeList[i-1], "vitamin5").toDouble()) * servingAmountValue / 100.0)
                vitamin5View.text = String.format("%.1f", vitamin5)
                vitamin6 = ((readData2(codeList[i-1], "vitamin6").toDouble()) * servingAmountValue / 100.0)
                vitamin6View.text = String.format("%.1f", vitamin6)
                vitamin7 = ((readData2(codeList[i-1], "vitamin7").toDouble()) * servingAmountValue / 100.0)
                vitamin7View.text = String.format("%.1f", vitamin7)
                mineral1 = ((readData2(codeList[i-1], "mineral1").toDouble()) * servingAmountValue / 100.0)
                mineral1View.text = String.format("%.1f", mineral1)
                mineral2 = ((readData2(codeList[i-1], "mineral2").toDouble()) * servingAmountValue / 100.0)
                mineral2View.text = String.format("%.1f", mineral2)
                mineral3 = ((readData2(codeList[i-1], "mineral3").toDouble()) * servingAmountValue / 100.0)
                mineral3View.text = String.format("%.1f", mineral3)
                mineral4 = ((readData2(codeList[i-1], "mineral4").toDouble()) * servingAmountValue / 100.0)
                mineral4View.text = String.format("%.1f", mineral4)
                mineral5 = ((readData2(codeList[i-1], "mineral5").toDouble()) * servingAmountValue / 100.0)
                mineral5View.text = String.format("%.1f", mineral5)
                mineral6 = ((readData2(codeList[i-1], "mineral6").toDouble()) * servingAmountValue / 100.0)
                mineral6View.text = String.format("%.1f", mineral6)
                mineral7 = ((readData2(codeList[i-1], "mineral7").toDouble()) * servingAmountValue / 100.0)
                mineral7View.text = String.format("%.1f", mineral7)
                mineral8 = ((readData2(codeList[i-1], "mineral8").toDouble()) * servingAmountValue / 100.0)
                mineral8View.text = String.format("%.1f", mineral8)
                mineral9 = ((readData2(codeList[i-1], "mineral9").toDouble()) * servingAmountValue / 100.0)
                mineral9View.text = String.format("%.1f", mineral9)
                mineral10 = ((readData2(codeList[i-1], "mineral10").toDouble()) * servingAmountValue / 100.0)
                mineral10View.text = String.format("%.1f", mineral10)
                other1 = ((readData2(codeList[i-1], "other1").toDouble()) * servingAmountValue / 100.0)
                other1View.text = String.format("%.1f", other1)
                other2 = ((readData2(codeList[i-1], "other2").toDouble()) * servingAmountValue / 100.0)
                other2View.text = String.format("%.1f", other2)
                other3 = ((readData2(codeList[i-1], "other3").toDouble()) * servingAmountValue / 100.0)
                other3View.text = String.format("%.1f", other3)
                other4 = ((readData2(codeList[i-1], "other4").toDouble()) * servingAmountValue / 100.0)
                other4View.text = String.format("%.1f", other4)
            }
        }
    }

    private fun readData(code: String): Bitmap {
        var image: Bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        image.eraseColor(Color.BLACK)
        val loadedImage = sharedPreferences.getString("${code}Image", "").toString()
        if (loadedImage != "") {
            val byteArray = Base64.decode(loadedImage, Base64.DEFAULT)
            image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }

        return image
    }

    private fun readData2(code: String, keyword: String): String {
        var key = ""
        when(keyword) {
            "type" -> key = "${code}Type"
            "liquid" -> key = "${code}Liquid"
            "calories" -> key = "${code}Calories"
            "fat" -> key = "${code}Fat"
            "fat1" -> key = "${code}Fat1"
            "fat2" -> key = "${code}Fat2"
            "fat3" -> key = "${code}Fat3"
            "fat4" -> key = "${code}Fat4"
            "protein" -> key = "${code}Protein"
            "carbs" -> key = "${code}Carbs"
            "carbs1" -> key = "${code}Carbs1"
            "carbs2" -> key = "${code}Carbs2"
            "vitamin1" -> key = "${code}Vitamin1"
            "vitamin2" -> key = "${code}Vitamin2"
            "vitamin3" -> key = "${code}Vitamin3"
            "vitamin4" -> key = "${code}Vitamin4"
            "vitamin5" -> key = "${code}Vitamin5"
            "vitamin6" -> key = "${code}Vitamin6"
            "vitamin7" -> key = "${code}Vitamin7"
            "mineral1" -> key = "${code}Mineral1"
            "mineral2" -> key = "${code}Mineral2"
            "mineral3" -> key = "${code}Mineral3"
            "mineral4" -> key = "${code}Mineral4"
            "mineral5" -> key = "${code}Mineral5"
            "mineral6" -> key = "${code}Mineral6"
            "mineral7" -> key = "${code}Mineral7"
            "mineral8" -> key = "${code}Mineral8"
            "mineral9" -> key = "${code}Mineral9"
            "mineral10" -> key = "${code}Mineral10"
            "other1" -> key = "${code}Other1"
            "other2" -> key = "${code}Other2"
            "other3" -> key = "${code}Other3"
            "other4" -> key = "${code}Other4"
        }

        val value = sharedPreferences.getString(key, "0") ?: "0"
        return value
    }

    private fun deleteData(code: String) {
        val editor = sharedPreferences.edit()

        editor.remove("${code}Type")
        editor.remove("${code}Liquid")
        editor.remove("${code}Calories")
        editor.remove("${code}Fat")
        editor.remove("${code}Fat1")
        editor.remove("${code}Fat2")
        editor.remove("${code}Fat3")
        editor.remove("${code}Fat4")
        editor.remove("${code}Protein")
        editor.remove("${code}Carbs")
        editor.remove("${code}Carbs1")
        editor.remove("${code}Carbs2")
        editor.remove("${code}Vitamin1")
        editor.remove("${code}Vitamin2")
        editor.remove("${code}Vitamin3")
        editor.remove("${code}Vitamin4")
        editor.remove("${code}Vitamin5")
        editor.remove("${code}Vitamin6")
        editor.remove("${code}Vitamin7")
        editor.remove("${code}Mineral1")
        editor.remove("${code}Mineral2")
        editor.remove("${code}Mineral3")
        editor.remove("${code}Mineral4")
        editor.remove("${code}Mineral5")
        editor.remove("${code}Mineral6")
        editor.remove("${code}Mineral7")
        editor.remove("${code}Mineral8")
        editor.remove("${code}Mineral9")
        editor.remove("${code}Mineral10")
        editor.remove("${code}Other1")
        editor.remove("${code}Other2")
        editor.remove("${code}Other3")
        editor.remove("${code}Other4")
        editor.apply()

        val context = this
        val codeList = SharedPreferenceHelper.getList(context).toMutableList()

        val iterator = codeList.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            if (element == code) {
                iterator.remove()
            }
        }

        editor.remove("ProductList")
        editor.apply()
        SharedPreferenceHelper.saveList(context, codeList)

        editor.remove("${code}Image")
        editor.remove("${code}Name")
        editor.remove("${code}Brand")
        editor.apply()

        val product = Intent(this, Search_Product::class.java)
        startActivity(product)
    }
}