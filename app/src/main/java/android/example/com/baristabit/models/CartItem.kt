package android.example.com.baristabit.models

import android.example.com.baristabit.CoffeeItem

data class CartItem(
    val name: String,
    var price: Double,
    val imageResource: Int,
    var quantity: Int
)