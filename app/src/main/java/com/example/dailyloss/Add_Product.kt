package com.example.dailyloss

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.InputStream
import android.util.Base64
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.ByteArrayOutputStream

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            onTextChanged(editable.toString())
        }
    })
}

class Add_Product : AppCompatActivity() {
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var img: Bitmap

    private lateinit var nameValue: EditText
    private lateinit var product_name: String
    private lateinit var imageView: ImageView
    private lateinit var image: String
    private lateinit var brandValue: EditText
    private var brand: String = ""
    private lateinit var typeValue: Spinner
    private lateinit var type: String
    private lateinit var liquidValue: EditText
    private var liquid: String = ""
    private lateinit var caloriesValue: EditText
    private lateinit var calories: String
    private lateinit var fatValue: EditText
    private lateinit var fat:String
    private lateinit var fat1Value: EditText
    private var fat1:String = "0"
    private lateinit var fat2Value: EditText
    private var fat2:String = "0"
    private lateinit var fat3Value: EditText
    private var fat3:String = "0"
    private lateinit var fat4Value: EditText
    private var fat4:String = "0"
    private lateinit var proteinValue: EditText
    private lateinit var protein: String
    private lateinit var carbsValue: EditText
    private lateinit var carbs: String
    private lateinit var carbs1Value: EditText
    private var carbs1: String = "0"
    private lateinit var carbs2Value: EditText
    private var carbs2: String = "0"
    private lateinit var vitamin1Value: EditText
    private var vitamin1: String = "0"
    private lateinit var vitamin2Value: EditText
    private var vitamin2: String = "0"
    private lateinit var vitamin3Value: EditText
    private var vitamin3: String = "0"
    private lateinit var vitamin4Value: EditText
    private var vitamin4: String = "0"
    private lateinit var vitamin5Value: EditText
    private var vitamin5: String = "0"
    private lateinit var vitamin6Value: EditText
    private var vitamin6: String = "0"
    private lateinit var vitamin7Value: EditText
    private var vitamin7: String = "0"
    private lateinit var mineral1Value: EditText
    private var mineral1: String = "0"
    private lateinit var mineral2Value: EditText
    private var mineral2: String = "0"
    private lateinit var mineral3Value: EditText
    private var mineral3: String = "0"
    private lateinit var mineral4Value: EditText
    private var mineral4: String = "0"
    private lateinit var mineral5Value: EditText
    private var mineral5: String = "0"
    private lateinit var mineral6Value: EditText
    private var mineral6: String = "0"
    private lateinit var mineral7Value: EditText
    private var mineral7: String = "0"
    private lateinit var mineral8Value: EditText
    private var mineral8: String = "0"
    private lateinit var mineral9Value: EditText
    private var mineral9: String = "0"
    private lateinit var mineral10Value: EditText
    private var mineral10: String = "0"
    private lateinit var other1Value: EditText
    private var other1: String = "0"
    private lateinit var other2Value: EditText
    private var other2: String = "0"
    private lateinit var other3Value: EditText
    private var other3: String = "0"
    private lateinit var other4Value: EditText
    private var other4: String = "0"
    private lateinit var nutritionTitle: TextView
    private lateinit var buttonSelectImage: Button
    private lateinit var buttonCreate: Button

    private var check1 = false
    private var check2 = false
    private var check4 = false
    private var check5 = false
    private var check6 = false
    private var check7 = false
    private var check8 = false
    private var check9 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Inicjalizacja widoków
        nameValue = findViewById(R.id.add_product_nameValue)
        imageView = findViewById(R.id.add_product_imageView)
        brandValue = findViewById(R.id.add_product_brandValue)
        typeValue = findViewById(R.id.add_product_typeValue)
        liquidValue = findViewById(R.id.add_product_liquidValue)
        caloriesValue = findViewById(R.id.add_product_caloriesValue)
        fatValue = findViewById(R.id.add_product_fatValue)
        fat1Value = findViewById(R.id.add_product_fat_1_Value)
        fat2Value = findViewById(R.id.add_product_fat_2_Value)
        fat3Value = findViewById(R.id.add_product_fat_3_Value)
        fat4Value = findViewById(R.id.add_product_fat_4_Value)
        proteinValue = findViewById(R.id.add_product_proteinValue)
        carbsValue = findViewById(R.id.add_product_carbsValue)
        carbs1Value = findViewById(R.id.add_product_carbs_1_Value)
        carbs2Value = findViewById(R.id.add_product_carbs_2_Value)
        vitamin1Value = findViewById(R.id.add_product_vitamin_1_Value)
        vitamin2Value = findViewById(R.id.add_product_vitamin_2_Value)
        vitamin3Value = findViewById(R.id.add_product_vitamin_3_Value)
        vitamin4Value = findViewById(R.id.add_product_vitamin_4_Value)
        vitamin5Value = findViewById(R.id.add_product_vitamin_5_Value)
        vitamin6Value = findViewById(R.id.add_product_vitamin_6_Value)
        vitamin7Value = findViewById(R.id.add_product_vitamin_7_Value)
        mineral1Value = findViewById(R.id.add_product_mineral_1_Value)
        mineral2Value = findViewById(R.id.add_product_mineral_2_Value)
        mineral3Value = findViewById(R.id.add_product_mineral_3_Value)
        mineral4Value = findViewById(R.id.add_product_mineral_4_Value)
        mineral5Value = findViewById(R.id.add_product_mineral_5_Value)
        mineral6Value = findViewById(R.id.add_product_mineral_6_Value)
        mineral7Value = findViewById(R.id.add_product_mineral_7_Value)
        mineral8Value = findViewById(R.id.add_product_mineral_8_Value)
        mineral9Value = findViewById(R.id.add_product_mineral_9_Value)
        mineral10Value = findViewById(R.id.add_product_mineral_10_Value)
        other1Value = findViewById(R.id.add_product_other_1_Value)
        other2Value = findViewById(R.id.add_product_other_2_Value)
        other3Value = findViewById(R.id.add_product_other_3_Value)
        other4Value = findViewById(R.id.add_product_other_4_Value)

        nutritionTitle = findViewById(R.id.add_product_amountTitle)
        buttonSelectImage = findViewById(R.id.add_product_imageValue)
        buttonCreate = findViewById(R.id.add_product_create)
        val option5 = findViewById<View>(R.id.add_product_option5Layout)

        val context = this
        val codeList: MutableList<String> = if (SharedPreferenceHelper.isListExists(context)) {
            SharedPreferenceHelper.getList(context).toMutableList()
        } else {
            mutableListOf()
        }

        // Przycisk "Create"
        buttonCreate.isEnabled = false
        buttonCreate.setOnClickListener {
            val code = brand + product_name
            if (!codeList.contains(code)) {
                codeList.add(code)
                SharedPreferenceHelper.saveList(context, codeList)
            }

            var key = "${code}Name"
            editor.putString(key, product_name)
            nameValue.text.clear()

            key = "${code}Image"
            editor.putString(key, image)
            imageView.visibility = View.GONE
            buttonSelectImage.text = getString(R.string.add_product_page_5)

            key = "${code}Brand"
            editor.putString(key, brand)
            brandValue.text.clear()

            key = "${code}Type"
            editor.putString(key, type)
            typeValue.setSelection(0)

            key = "${code}Liquid"
            editor.putString(key, liquid)
            liquidValue.text.clear()

            key = "${code}Calories"
            editor.putString(key, calories)
            caloriesValue.text.clear()

            key = "${code}Fat"
            editor.putString(key, fat)
            fatValue.text.clear()

            key = "${code}Fat1"
            editor.putString(key, fat1)
            fat1Value.text.clear()

            key = "${code}Fat2"
            editor.putString(key, fat2)
            fat2Value.text.clear()

            key = "${code}Fat3"
            editor.putString(key, fat3)
            fat3Value.text.clear()

            key = "${code}Fat4"
            editor.putString(key, fat4)
            fat4Value.text.clear()

            key = "${code}Protein"
            editor.putString(key, protein)
            proteinValue.text.clear()

            key = "${code}Carbs"
            editor.putString(key, carbs)
            carbsValue.text.clear()

            key = "${code}Carbs1"
            editor.putString(key, carbs1)
            carbs1Value.text.clear()

            key = "${code}Carbs2"
            editor.putString(key, carbs2)
            carbs2Value.text.clear()

            key = "${code}Vitamin1"
            editor.putString(key, vitamin1)
            vitamin1Value.text.clear()

            key = "${code}Vitamin2"
            editor.putString(key, vitamin2)
            vitamin2Value.text.clear()

            key = "${code}Vitamin3"
            editor.putString(key, vitamin3)
            vitamin3Value.text.clear()

            key = "${code}Vitamin4"
            editor.putString(key, vitamin4)
            vitamin4Value.text.clear()

            key = "${code}Vitamin5"
            editor.putString(key, vitamin5)
            vitamin5Value.text.clear()

            key = "${code}Vitamin6"
            editor.putString(key, vitamin6)
            vitamin6Value.text.clear()

            key = "${code}Vitamin7"
            editor.putString(key, vitamin7)
            vitamin7Value.text.clear()

            key = "${code}Mineral1"
            editor.putString(key, mineral1)
            mineral1Value.text.clear()

            key = "${code}Mineral2"
            editor.putString(key, mineral2)
            mineral2Value.text.clear()

            key = "${code}Mineral3"
            editor.putString(key, mineral3)
            mineral3Value.text.clear()

            key = "${code}Mineral4"
            editor.putString(key, mineral4)
            mineral4Value.text.clear()

            key = "${code}Mineral5"
            editor.putString(key, mineral5)
            mineral5Value.text.clear()

            key = "${code}Mineral6"
            editor.putString(key, mineral6)
            mineral6Value.text.clear()

            key = "${code}Mineral7"
            editor.putString(key, mineral7)
            mineral7Value.text.clear()

            key = "${code}Mineral8"
            editor.putString(key, mineral8)
            mineral8Value.text.clear()

            key = "${code}Mineral9"
            editor.putString(key, mineral9)
            mineral9Value.text.clear()

            key = "${code}Mineral10"
            editor.putString(key, mineral10)
            mineral10Value.text.clear()

            key = "${code}Other1"
            editor.putString(key, other1)
            other1Value.text.clear()

            key = "${code}Other2"
            editor.putString(key, other2)
            other2Value.text.clear()

            key = "${code}Other3"
            editor.putString(key, other3)
            other3Value.text.clear()

            key = "${code}Other4"
            editor.putString(key, other4)
            other4Value.text.clear()

            editor.apply()
            Toast.makeText(this, R.string.add_product_page_47, Toast.LENGTH_SHORT).show()
        }

        // Spinner Adapter
        ArrayAdapter.createFromResource(
            this,
            R.array.product_type,
            R.layout.add_product_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            typeValue.adapter = adapter
        }

        // Zainicjuj launcher
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri: Uri? = data?.data
                imageUri?.let {
                    processImage(it)
                }
            }
        }

        // Przycisk do wybrania zdjęcia
        buttonSelectImage.setOnClickListener {
            openGallery()
        }

        // Aktualizacja EditText'ów && Zabezpieczenie przycisku "Create"
        nameValue.onTextChanged { newText ->
            check1 = newText.isNotEmpty()
            product_name = newText
            checkButton()
        }
        brandValue.onTextChanged { newText ->
            brand = newText
        }
        typeValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        check4 = true
                        check5 = true
                        option5.visibility = View.GONE
                        nutritionTitle.text = getString(R.string.add_product_page_10)
                        type = parent.getItemAtPosition(position).toString()
                        checkButton()
                    }
                    1 -> {
                        check4 = true
                        check5 = false
                        type = parent.getItemAtPosition(position).toString()
                        nutritionTitle.text = getString(R.string.add_product_page_50)
                        liquid = ""
                        checkButton()
                        liquidValue.onTextChanged { newText ->
                            check5 = newText.isNotEmpty()
                            liquid = newText
                            checkButton()
                        }
                        option5.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        caloriesValue.onTextChanged { newText ->
            check6 = newText.isNotEmpty()
            calories = newText
            checkButton()
        }
        fatValue.onTextChanged { newText ->
            check7 = newText.isNotEmpty()
            fat = newText
            checkButton()
        }
        fat1Value.onTextChanged { newText ->
            fat1 = newText
        }
        fat2Value.onTextChanged { newText ->
            fat2 = newText
        }
        fat3Value.onTextChanged { newText ->
            fat3 = newText
        }
        fat4Value.onTextChanged { newText ->
            fat4 = newText
        }
        proteinValue.onTextChanged { newText ->
            check8 = newText.isNotEmpty()
            protein = newText
            checkButton()
        }
        carbsValue.onTextChanged { newText ->
            check9 = newText.isNotEmpty()
            carbs = newText
            checkButton()
        }
        carbs1Value.onTextChanged { newText ->
            carbs1 = newText
        }
        carbs2Value.onTextChanged { newText ->
            carbs2 = newText
        }
        vitamin1Value.onTextChanged { newText ->
            vitamin1 = newText
        }
        vitamin2Value.onTextChanged { newText ->
            vitamin2 = newText
        }
        vitamin3Value.onTextChanged { newText ->
            vitamin3 = newText
        }
        vitamin4Value.onTextChanged { newText ->
            vitamin4 = newText
        }
        vitamin5Value.onTextChanged { newText ->
            vitamin5 = newText
        }
        vitamin6Value.onTextChanged { newText ->
            vitamin6 = newText
        }
        vitamin7Value.onTextChanged { newText ->
            vitamin7 = newText
        }
        mineral1Value.onTextChanged { newText ->
            mineral1 = newText
        }
        mineral2Value.onTextChanged { newText ->
            mineral2 = newText
        }
        mineral3Value.onTextChanged { newText ->
            mineral3 = newText
        }
        mineral4Value.onTextChanged { newText ->
            mineral4 = newText
        }
        mineral5Value.onTextChanged { newText ->
            mineral5 = newText
        }
        mineral6Value.onTextChanged { newText ->
            mineral6 = newText
        }
        mineral7Value.onTextChanged { newText ->
            mineral7 = newText
        }
        mineral8Value.onTextChanged { newText ->
            mineral8 = newText
        }
        mineral9Value.onTextChanged { newText ->
            mineral9 = newText
        }
        mineral10Value.onTextChanged { newText ->
            mineral10 = newText
        }
        other1Value.onTextChanged { newText ->
            other1 = newText
        }
        other2Value.onTextChanged { newText ->
            other2 = newText
        }
        other3Value.onTextChanged { newText ->
            other3 = newText
        }
        other4Value.onTextChanged { newText ->
            other4 = newText
        }
    }

    // Funkcja otwierająca galerię
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    // Funkcja do przetwarzania obrazu
    private fun processImage(imageUri: Uri) {
        val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
        inputStream?.let {
            val originalBitmap = BitmapFactory.decodeStream(it)
            img = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true)
            imageView.setImageBitmap(img)
            check2 = true
            checkButton()
            buttonSelectImage.text = getString(R.string.add_product_page_46)
            imageView.visibility = View.VISIBLE

            val byteArrayOutputStream = ByteArrayOutputStream()
            img.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            image = Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }

    private fun checkButton() {
        if(check1 && check2 && check4 && check5 && check6 && check7 && check8 && check9) {
            buttonCreate.isEnabled = true
        } else {
            buttonCreate.isEnabled = false
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val dailyMeal = Intent(this, Daily_Meal::class.java)
        startActivity(dailyMeal)
    }
}