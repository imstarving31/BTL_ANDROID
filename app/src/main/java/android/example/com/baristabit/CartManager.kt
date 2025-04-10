package android.example.com.baristabit

object CartManager {
    // Lưu trữ các mặt hàng trong giỏ hàng
    private val selectedItems = mutableListOf<CoffeeItem>()

    // Thêm sản phẩm vào giỏ hàng
    fun addToCart(item: CoffeeItem) {
        // Kiểm tra xem đã có trong giỏ hàng chưa
        val existingItem = selectedItems.find { it.name == item.name }
        if (existingItem != null) {
            // Nếu đã có, cập nhật số lượng
            existingItem.quantity = item.quantity
            existingItem.price = calculatePrice(item)
        } else {
            // Nếu chưa có, thêm mới vào giỏ hàng
            val newItem = item.copy(
                quantity = item.quantity,
                isSelected = true,
                price = calculatePrice(item)
            )
            selectedItems.add(newItem)
        }
    }

    // Tính giá dựa trên số lượng
    private fun calculatePrice(item: CoffeeItem): Double {
        // Giả sử giá gốc là giá cho 1 đơn vị
        val basePrice = item.price / (if (item.quantity > 0) item.quantity else 1)
        return basePrice * item.quantity
    }

    // Cập nhật số lượng trong giỏ hàng
    fun updateQuantity(item: CoffeeItem, newQuantity: Int) {
        val cartItem = selectedItems.find { it.name == item.name }
        cartItem?.let {
            it.quantity = newQuantity
            it.price = calculatePrice(it)

            // Đồng bộ với danh sách chính
            val coffeeItem = CoffeeData.findCoffeeByName(it.name)
            coffeeItem?.quantity = newQuantity
        }
    }

    // Xóa khỏi giỏ hàng
    fun removeFromCart(item: CoffeeItem) {
        selectedItems.removeIf { it.name == item.name }

        // Đồng bộ với danh sách chính
        val coffeeItem = CoffeeData.findCoffeeByName(item.name)
        coffeeItem?.isSelected = false
    }

    // Lấy danh sách các item đã chọn
    fun getSelectedItems(): List<CoffeeItem> {
        return selectedItems.toList()
    }

    // Tính tổng tiền
    fun getTotal(): Double {
        return selectedItems.sumOf { it.price * it.quantity}
    }
}