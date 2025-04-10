package android.example.com.baristabit

data class Order2(
    val id: String,
    val customerName: String,
    val orderTime: String,
    val items: List<OrderItem>,
    val orderType: String,  // "takeaway", "delivery", "home"
    val doneTime: String
)

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double? = null
)