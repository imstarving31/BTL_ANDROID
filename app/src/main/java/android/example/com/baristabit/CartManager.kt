package android.example.com.baristabit

object CartManager {
    val cartItems = mutableListOf<CoffeeItem>()

    fun addItem(item: CoffeeItem) {
        // Nếu item đã tồn tại thì tăng số lượng
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            updateQuantity(existingItem,existingItem.quantity+item.quantity)
        } else {
            val cartItem = item.copy(isSelected = true)
            cartItems.add(cartItem)
        }
    }

    fun updateQuantity(item: CoffeeItem, newQuantity: Int) {
        val index = cartItems.indexOfFirst { it.name == item.name }
        if (index != -1) {
            cartItems[index].quantity = newQuantity
        }
    }

    fun getSelectedItems(): List<CoffeeItem> {
        return cartItems.filter { it.isSelected }
    }

    fun getTotal(): Double {
        return cartItems.sumOf { it.price * it.quantity }
    }
}
