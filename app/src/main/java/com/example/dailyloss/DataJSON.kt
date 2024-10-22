package com.example.dailyloss

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferenceHelper {
    private const val PREFS_NAME = "my_preferences"
    private const val LIST_KEY = "ProductList"

    // Funkcja do zapisu listy w SharedPreferences
    fun saveList(context: Context, list: List<String>) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        // Konwersja listy na JSON
        val gson = Gson()
        val json = gson.toJson(list)

        // Zapis JSON-a w SharedPreferences
        editor.putString(LIST_KEY, json)
        editor.apply()
    }

    // Funkcja do odczytania listy z SharedPreferences
    fun getList(context: Context): List<String> {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Pobranie JSON-a z SharedPreferences
        val json = prefs.getString(LIST_KEY, null)

        return if (json != null) {
            // Jeśli JSON istnieje, konwersja go na listę
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(json, type)
        } else {
            // Jeśli JSON nie istnieje, zwrócenie pustej listy
            listOf()
        }
    }

    // Funkcja do sprawdzenia, czy lista istnieje
    fun isListExists(context: Context): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.contains(LIST_KEY)
    }
}