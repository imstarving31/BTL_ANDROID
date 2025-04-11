package android.example.com.baristabit.models

import android.net.Uri
import java.io.Serializable

data class ProductItem (
    val imageUri: String,
    val productName: String,
    val productDescription: String,
    val productCost: String
) : Serializable