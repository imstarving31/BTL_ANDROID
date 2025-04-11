package android.example.com.baristabit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CoffeeStorage {
    private const val PREF_NAME = "coffee_pref"
    private const val KEY_LIST = "coffee_list"

    private val gson = Gson()

    fun saveList(context: Context, list: List<CoffeeItem>) {
        val json = gson.toJson(list)
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LIST, json).apply()
    }

    fun loadList(context: Context): MutableList<CoffeeItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_LIST, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<CoffeeItem>>() {}.type
        return gson.fromJson(json, type)
    }
}
