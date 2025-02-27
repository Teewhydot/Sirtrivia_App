package com.sirteefyapps.sirtrivia.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Save a Map to SharedPreferences
    fun saveUserProgress(key: String, map: Map<String, Any>) {
        val jsonString = gson.toJson(map)
        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    // Retrieve a Map from SharedPreferences
    fun getUserProgress(key: String): Map<String, Any>? {
        val jsonString = sharedPreferences.getString(key, null)
        return if (jsonString != null) {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            gson.fromJson(jsonString, type)
        } else {
            null
        }
    }

    // Clear a specific key from SharedPreferences
    fun clearKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
