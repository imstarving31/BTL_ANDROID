data class Order(
    val customerName: String,
    val orderTime: String,
    val items: List<String>,
    val isTakeAway: Boolean
)
