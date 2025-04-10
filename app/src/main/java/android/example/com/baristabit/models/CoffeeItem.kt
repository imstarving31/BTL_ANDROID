package android.example.com.baristabit

data class CoffeeItem(
    val name: String,
    val rating: Double,
    val description: String,
    val imageResId: Int,
    var isFavorite: Boolean = false,
    var price: Double,
    var quantity: Int,
    var isSelected: Boolean = false
)