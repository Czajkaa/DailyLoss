package com.example.dailyloss

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.window.OnBackInvokedDispatcher

class Profile : AppCompatActivity() {
    private val sharedPreferencesName = "ScrollPosition"
    private lateinit var scrollView: ScrollView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // ScrollView position

        scrollView = findViewById(R.id.profile_scrollView)

        // Name and Subname

        val nameItem = findViewById<EditText>(R.id.profile_name)
        val subnameItem = findViewById<EditText>(R.id.profile_subname)

        nameItem.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editor.putString("name_value", s.toString())
                editor.apply()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        nameItem.setText(sharedPreferences.getString("name_value", ""))

        subnameItem.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editor.putString("subname_value", s.toString())
                editor.apply()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        subnameItem.setText(sharedPreferences.getString("subname_value", ""))

        // Settings 1 (age, gender)

        var age = sharedPreferences.getInt("age_value", 0)
        val ageItem = findViewById<TextView>(R.id.profile_ageValue)
        val agePlus = findViewById<ImageButton>(R.id.profile_agePlus)
        val ageMinus = findViewById<ImageButton>(R.id.profile_ageMinus)

        ageItem.text = age.toString()

        agePlus.setOnClickListener {
            age++
            ageItem.text = age.toString()
            with(sharedPreferences.edit()) {
                putInt("age_value", age)
                apply()
            }
            updateStats()
        }
        ageMinus.setOnClickListener {
            age--
            ageItem.text = age.toString()
            with(sharedPreferences.edit()) {
                putInt("age_value", age)
                apply()
            }
            updateStats()
        }

        val gender = (sharedPreferences.getString("gender_value", "male")).toString()
        val maleButton = findViewById<ImageButton>(R.id.profile_genderMale)
        val femaleButton = findViewById<ImageButton>(R.id.profile_genderFemale)

        if(gender == "male") {
            maleButton.isSelected = true
            femaleButton.isSelected = false
        }
        if(gender == "female") {
            maleButton.isSelected = false
            femaleButton.isSelected = true
        }

        maleButton.setOnClickListener {
            if(!maleButton.isSelected) {
                maleButton.isSelected = true
            }
            if(femaleButton.isSelected) {
                femaleButton.isSelected = !femaleButton.isSelected
            }
            with(sharedPreferences.edit()) {
                putString("gender_value", "male")
                apply()
            }
            updateStats()
        }
        femaleButton.setOnClickListener {
            if(!femaleButton.isSelected) {
                femaleButton.isSelected = true
            }
            if(maleButton.isSelected) {
                maleButton.isSelected = !maleButton.isSelected
            }
            with(sharedPreferences.edit()) {
                putString("gender_value", "female")
                apply()
            }
            updateStats()
        }

        // Settings 2 (height, weight)

        val heightItem = findViewById<EditText>(R.id.profile_heightValue)
        heightItem.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString() != "") {
                    if(s.toString().toInt() <= 120 || s.toString().toInt() >= 300) {
                        editor.putString("height_value", "0")
                        editor.apply()
                        updateStats()
                    } else {
                        editor.putString("height_value", s.toString())
                        editor.apply()
                        updateStats()
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        heightItem.setText(sharedPreferences.getString("height_value", "0"))

        val weightItem = findViewById<EditText>(R.id.profile_weightValue)
        weightItem.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString() != "") {
                    if(s.toString().toDouble() <= 40.0 || s.toString().toDouble() >= 300.0) {
                        editor.putString("weight_value", "0")
                        editor.apply()
                        updateStats()
                    } else {
                        editor.putString("weight_value", s.toString())
                        editor.apply()
                        updateStats()
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        weightItem.setText(sharedPreferences.getString("weight_value", "0"))

        // Settings 3 (lean body mass)

        val LBMItem = findViewById<EditText>(R.id.profile_LBMValue)
        LBMItem.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString() != "") {
                    editor.putString("LBM_value", s.toString())
                    editor.apply()
                    updateStats()
                } else {
                    editor.putString("LBM_value", "0.0")
                    editor.apply()
                    updateStats()
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        LBMItem.setText((sharedPreferences.getString("LBM_value", "0.0"))!!.toDouble().toString())

        // Spinners

        val activityItem = findViewById<Spinner>(R.id.profile_activityValue)
        ArrayAdapter.createFromResource(
            this,
            R.array.activity,
            R.layout.spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            activityItem.adapter = adapter
        }

        val activityPosition = sharedPreferences.getInt("activity_value", 0)
        activityItem.setSelection(activityPosition)

        activityItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    with(sharedPreferences.edit()) {
                        putInt("activity_value", position)
                        apply()
                        updateStats()
                    }
                } else {
                    Log.e("Profile", "View is null in onItemSelected")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val goalItem = findViewById<Spinner>(R.id.profile_goalValue)
        ArrayAdapter.createFromResource(
            this,
            R.array.goal,
            R.layout.spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            goalItem.adapter = adapter
        }

        val goalPosition = sharedPreferences.getInt("goal_value", 0)
        goalItem.setSelection(goalPosition)

        goalItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    with(sharedPreferences.edit()) {
                        putInt("goal_value", position)
                        apply()
                        updateStats()
                    }
                } else {
                    Log.e("Profile", "View is null in onItemSelected")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        updateStats()

        // Stats buttons

        val bmrButton = findViewById<Button>(R.id.profile_bmrButton)
        bmrButton.setOnClickListener {
            val bmrPage = Intent(this, Stats_BMR::class.java)
            startActivity(bmrPage)
        }

        val bmiButton = findViewById<Button>(R.id.profile_bmiButton)
        bmiButton.setOnClickListener {
            val bmiPage = Intent(this, Stats_BMI::class.java)
            startActivity(bmiPage)
        }
        val tdeeButton = findViewById<Button>(R.id.profile_tdeeButton)
        tdeeButton.setOnClickListener {
            val tdeePage = Intent(this, Stats_TDEE::class.java)
            startActivity(tdeePage)
        }
        val rmrButton = findViewById<Button>(R.id.profile_rmrButton)
        rmrButton.setOnClickListener {
            val rmrPage = Intent(this, Stats_RMR::class.java)
            startActivity(rmrPage)
        }
        val ibwButton = findViewById<Button>(R.id.profile_ibwButton)
        ibwButton.setOnClickListener {
            val ibwPage = Intent(this, Stats_IBW::class.java)
            startActivity(ibwPage)
        }
        val lbwButton = findViewById<Button>(R.id.profile_lbwButton)
        lbwButton.setOnClickListener {
            val lbwPage = Intent(this, Stats_LBW::class.java)
            startActivity(lbwPage)
        }
        val dwiButton = findViewById<Button>(R.id.profile_dwiButton)
        dwiButton.setOnClickListener {
            val dwiPage = Intent(this, Stats_DWI::class.java)
            startActivity(dwiPage)
        }
        val bfpButton = findViewById<Button>(R.id.profile_bfpButton)
        bfpButton.setOnClickListener {
            val bfpPage = Intent(this, Stats_BFP::class.java)
            startActivity(bfpPage)
        }
        val macroButton = findViewById<Button>(R.id.profile_macro)
        macroButton.setOnClickListener {
            val macroPage = Intent(this, Stats_Macro::class.java)
            startActivity(macroPage)
        }
    }

    // Stats
    fun updateStats() {
        val sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

        val bmrButton = findViewById<Button>(R.id.profile_bmrButton)
        val bmiButton = findViewById<Button>(R.id.profile_bmiButton)
        val tdeeButton = findViewById<Button>(R.id.profile_tdeeButton)
        val rmrButton = findViewById<Button>(R.id.profile_rmrButton)
        val ibwButton = findViewById<Button>(R.id.profile_ibwButton)
        val lbwButton = findViewById<Button>(R.id.profile_lbwButton)
        val dwiButton = findViewById<Button>(R.id.profile_dwiButton)
        val bfpButton = findViewById<Button>(R.id.profile_bfpButton)

        bmrButton.text = chceck_stats(sharedPreferences, "BMR")
        bmiButton.text = chceck_stats(sharedPreferences, "BMI")
        tdeeButton.text = chceck_stats(sharedPreferences, "TDEE")
        rmrButton.text = chceck_stats(sharedPreferences, "RMR")
        ibwButton.text = chceck_stats(sharedPreferences, "IBW")
        lbwButton.text = chceck_stats(sharedPreferences, "LBW")
        dwiButton.text = chceck_stats(sharedPreferences, "DWI")
        bfpButton.text = chceck_stats(sharedPreferences, "BFP")
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val main = Intent(this, Main_Page::class.java)
        startActivity(main)
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        val scrollY = sharedPreferences.getInt("profile_scrollY", 0)
        scrollView.post {
            scrollView.scrollTo(0, scrollY)
        }
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("profile_scrollY", scrollView.scrollY)
        editor.apply()
    }
}