package android.example.com.baristabit.models

import android.net.Uri

data class ProductItem(
    val imageUri: Uri,
    val productName: String,
    val productDescription: String,
    val productCost: String
)