package android.example.com.baristabit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CoffeeStorage {
    private const val PREF_NAME = "coffee_data"
    private const val KEY_ITEMS = "coffee_items"

    fun saveCoffeeItems(context: Context, items: List<CoffeeItem>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(items)
        prefs.edit().putString(KEY_ITEMS, json).apply()
    }

    fun loadCoffeeItems(context: Context): MutableList<CoffeeItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_ITEMS, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<CoffeeItem>>() {}.type
        return Gson().fromJson(json, type)
    }
}