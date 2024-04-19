package com.example.dailyloss

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Main_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        water()
        val user = findViewById<ImageButton>(R.id.mainPage_imageButton2)
        val profile = Intent(this, Profile::class.java)
        user.setOnClickListener {
            startActivity(profile)
        }
    }


    private fun water() {
        val card1 = findViewById<ImageButton>(R.id.mainPage_card1_image)
        val card2 = findViewById<ImageButton>(R.id.mainPage_card2_image)
        val card3 = findViewById<ImageButton>(R.id.mainPage_card3_image)
        val card4 = findViewById<ImageButton>(R.id.mainPage_card4_image)
        val card5 = findViewById<ImageButton>(R.id.mainPage_card5_image)
        val card6 = findViewById<ImageButton>(R.id.mainPage_card6_image)
        val card7 = findViewById<ImageButton>(R.id.mainPage_card7_image)
        val card8 = findViewById<ImageButton>(R.id.mainPage_card8_image)
        val card9 = findViewById<ImageButton>(R.id.mainPage_card9_image)
        val card10 = findViewById<ImageButton>(R.id.mainPage_card10_image)

        var card1Status = 0
        var card2Status = 0
        var card3Status = 0
        var card4Status = 0
        var card5Status = 0
        var card6Status = 0
        var card7Status = 0
        var card8Status = 0
        var card9Status = 0
        var card10Status = 0

        card1.setOnClickListener {
            when(card1Status) {
                1 -> {
                    if (card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else if (card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1) {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                    else if (card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1) {
                        card6.setImageResource(R.drawable.empty_glass); card6Status = 0 }
                    else if (card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1) {
                        card5.setImageResource(R.drawable.empty_glass); card5Status = 0 }
                    else if (card2Status == 1 && card3Status == 1 && card4Status == 1) {
                        card4.setImageResource(R.drawable.empty_glass); card4Status = 0 }
                    else if (card2Status == 1 && card3Status == 1) {
                        card3.setImageResource(R.drawable.empty_glass); card3Status = 0 }
                    else if (card2Status == 1) {
                        card2.setImageResource(R.drawable.empty_glass); card2Status = 0 }
                    else {
                        card1.setImageResource(R.drawable.empty_glass); card1Status = 0 }
                }
                0 -> {
                    card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
            }
        }

        card2.setOnClickListener {
            when(card2Status) {
                1 -> {
                    if (card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else if (card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1) {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                    else if (card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1) {
                        card6.setImageResource(R.drawable.empty_glass); card6Status = 0 }
                    else if (card3Status == 1 && card4Status == 1 && card5Status == 1) {
                        card5.setImageResource(R.drawable.empty_glass); card5Status = 0 }
                    else if (card3Status == 1 && card4Status == 1) {
                        card4.setImageResource(R.drawable.empty_glass); card4Status = 0 }
                    else if (card3Status == 1) {
                        card3.setImageResource(R.drawable.empty_glass); card3Status = 0 }
                    else {
                        card2.setImageResource(R.drawable.empty_glass); card2Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                }
            }
        }

        card3.setOnClickListener {
            when(card3Status) {
                1 -> {
                    if (card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else if (card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1) {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                    else if (card4Status == 1 && card5Status == 1 && card6Status == 1) {
                        card6.setImageResource(R.drawable.empty_glass); card6Status = 0 }
                    else if (card4Status == 1 && card5Status == 1) {
                        card5.setImageResource(R.drawable.empty_glass); card5Status = 0 }
                    else if (card4Status == 1) {
                        card4.setImageResource(R.drawable.empty_glass); card4Status = 0 }
                    else {
                        card3.setImageResource(R.drawable.empty_glass); card3Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                }
            }
        }

        card4.setOnClickListener {
            when(card4Status) {
                1 -> {
                    if (card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else if (card5Status == 1 && card6Status == 1 && card7Status == 1) {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                    else if (card5Status == 1 && card6Status == 1) {
                        card6.setImageResource(R.drawable.empty_glass); card6Status = 0 }
                    else if (card5Status == 1) {
                        card5.setImageResource(R.drawable.empty_glass); card5Status = 0 }
                    else {
                        card4.setImageResource(R.drawable.empty_glass); card4Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                }
            }
        }

        card5.setOnClickListener {
            when(card5Status) {
                1 -> {
                    if (card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card6Status == 1 && card7Status == 1 && card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else if (card6Status == 1 && card7Status == 1) {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                    else if (card6Status == 1) {
                        card6.setImageResource(R.drawable.empty_glass); card6Status = 0 }
                    else {
                        card5.setImageResource(R.drawable.empty_glass); card5Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 0) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1) {
                        card5.setImageResource(R.drawable.full_glass); card5Status = 1 }
                }
            }
        }

        card6.setOnClickListener {
            when(card6Status) {
                1 -> {
                    if (card7Status == 1 && card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card7Status == 1 && card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else if (card7Status == 1) {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                    else {
                        card6.setImageResource(R.drawable.empty_glass); card6Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 0) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 0) {
                        card5.setImageResource(R.drawable.full_glass); card5Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1) {
                        card6.setImageResource(R.drawable.full_glass); card6Status = 1 }
                }
            }
        }

        card7.setOnClickListener {
            when(card7Status) {
                1 -> {
                    if (card8Status == 1 && card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card8Status == 1 && card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else if (card8Status == 1) {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                    else {
                        card7.setImageResource(R.drawable.empty_glass); card7Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 0) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 0) {
                        card5.setImageResource(R.drawable.full_glass); card5Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 0) {
                        card6.setImageResource(R.drawable.full_glass); card6Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1) {
                        card7.setImageResource(R.drawable.full_glass); card7Status = 1 }
                }
            }
        }

        card8.setOnClickListener {
            when(card8Status) {
                1 -> {
                    if (card9Status == 1 && card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else if (card9Status == 1) {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                    else {
                        card8.setImageResource(R.drawable.empty_glass); card8Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 0) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 0) {
                        card5.setImageResource(R.drawable.full_glass); card5Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 0) {
                        card6.setImageResource(R.drawable.full_glass); card6Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 0) {
                        card7.setImageResource(R.drawable.full_glass); card7Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1) {
                        card8.setImageResource(R.drawable.full_glass); card8Status = 1 }
                }
            }
        }

        card9.setOnClickListener {
            when(card9Status) {
                1 -> {
                    if (card10Status == 1) {
                        card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                    else {
                        card9.setImageResource(R.drawable.empty_glass); card9Status = 0 }
                }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 0) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 0) {
                        card5.setImageResource(R.drawable.full_glass); card5Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 0) {
                        card6.setImageResource(R.drawable.full_glass); card6Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 0) {
                        card7.setImageResource(R.drawable.full_glass); card7Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 0) {
                        card8.setImageResource(R.drawable.full_glass); card8Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1) {
                        card9.setImageResource(R.drawable.full_glass); card9Status = 1 }
                }
            }
        }

        card10.setOnClickListener {
            when(card10Status) {
                1 -> { card10.setImageResource(R.drawable.empty_glass); card10Status = 0 }
                0 -> {
                    if (card1Status == 0) {
                        card1.setImageResource(R.drawable.full_glass); card1Status = 1 }
                    else if (card1Status == 1 && card2Status == 0) {
                        card2.setImageResource(R.drawable.full_glass); card2Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 0) {
                        card3.setImageResource(R.drawable.full_glass); card3Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 0) {
                        card4.setImageResource(R.drawable.full_glass); card4Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 0) {
                        card5.setImageResource(R.drawable.full_glass); card5Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 0) {
                        card6.setImageResource(R.drawable.full_glass); card6Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 0) {
                        card7.setImageResource(R.drawable.full_glass); card7Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 0) {
                        card8.setImageResource(R.drawable.full_glass); card8Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 0) {
                        card9.setImageResource(R.drawable.full_glass); card9Status = 1 }
                    else if (card1Status == 1 && card2Status == 1 && card3Status == 1 && card4Status == 1 && card5Status == 1 && card6Status == 1 && card7Status == 1 && card8Status == 1 && card9Status == 1) {
                        card10.setImageResource(R.drawable.full_glass); card10Status = 1 }
                }
            }
        }
    }
}