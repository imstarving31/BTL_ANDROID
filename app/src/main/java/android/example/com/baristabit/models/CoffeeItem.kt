package android.example.com.baristabit

data class CoffeeItem(
    val name: String,
    val rating: Float,
    val description: String,
    val imageResId: Int,
    var isFavorite: Boolean = false
)