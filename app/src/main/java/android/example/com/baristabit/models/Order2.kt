package android.example.com.baristabit

data class Order2(
    val id: String,
    val customerName: String,
    val orderTime: String,
    val items: List<String>,
    val orderType: String,
    val doneTime: String
)

