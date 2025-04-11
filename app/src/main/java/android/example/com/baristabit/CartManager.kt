package android.example.com.baristabit

object CartManager {
    private val selectedItems = mutableListOf<CoffeeItem>()

    fun getSelectedItems(): List<CoffeeItem> = selectedItems

    fun addItem(item: CoffeeItem) {
        val existing = selectedItems.find { it.name == item.name }
        if (existing != null) {
            existing.quantity = item.quantity
        } else {
            selectedItems.add(item)
        }
    }

    fun updateQuantity(item: CoffeeItem, newQuantity: Int) {
        selectedItems.find { it.name == item.name }?.quantity = newQuantity
    }

    fun getTotal(): Double {
        return selectedItems.sumOf { it.price *it.quantity }
    }

    fun clear() {
        selectedItems.clear()
    }
}
