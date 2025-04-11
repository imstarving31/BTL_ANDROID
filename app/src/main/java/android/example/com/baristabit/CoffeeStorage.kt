package android.example.com.baristabit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CoffeeStorage {
    private const val PREF_NAME = "coffee_pref"
    private const val KEY_LIST = "coffee_list"
    private val gson = Gson()

    fun saveList(context: Context, list: List<CoffeeItem>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LIST, gson.toJson(list)).apply()
    }

    fun getCoffeeList(context: Context): MutableList<CoffeeItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_LIST, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<CoffeeItem>>() {}.type
        return gson.fromJson(json, type)
    }

    fun findItemByName(context: Context, name: String): CoffeeItem? {
        return getCoffeeList(context).find { it.name.equals(name, ignoreCase = true) }
    }

    fun updateItem(context: Context, updatedItem: CoffeeItem) {
        val list = getCoffeeList(context)
        val index = list.indexOfFirst { it.name == updatedItem.name }
        if (index != -1) {
            list[index] = updatedItem
            saveList(context, list)
        }
    }
}

