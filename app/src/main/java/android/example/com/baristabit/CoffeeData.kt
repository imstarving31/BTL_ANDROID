package android.example.com.baristabit

import android.content.Context

object CoffeeData {
    var coffeeItems: MutableList<CoffeeItem> = mutableListOf()

    fun initialize(context: Context) {
        coffeeItems = CoffeeStorage.loadCoffeeItems(context)
        if (coffeeItems.isEmpty()) {
            coffeeItems.addAll(
                listOf(
                    CoffeeItem("Cappuccino", 4.9, "an Italian classic...", R.drawable.cappuccino, false, 2.0, 1, true),
                    CoffeeItem("Black Coffee", 4.8, "Black coffee is a pure delight...", R.drawable.black_coffee, false, 2.0, 1),
                    CoffeeItem("Vietnam Ice Coffee", 5.0, "Vietnamese Iced Coffee – a Vietnamese specialty...", R.drawable.vietnam_ice_coffee, false, 1.18, 1),
                    CoffeeItem("Croissant", 4.6, "Croissant – a classic French pastry...", R.drawable.croissant, false, 3.6, 1)
                )
            )
            CoffeeStorage.saveCoffeeItems(context, coffeeItems)
        }
    }

    fun addCoffeeItem(context: Context, item: CoffeeItem) {
        coffeeItems.add(item)
        CoffeeStorage.saveCoffeeItems(context, coffeeItems)
    }

    fun updateCoffee(name: String, quantity: Int, isSelected: Boolean) {
        val coffee = findCoffeeByName(name)
        coffee?.let {
            it.quantity = quantity
            it.isSelected = isSelected
            if (isSelected) {
                CartManager.addToCart(it)
            } else {
                CartManager.removeFromCart(it)
            }
        }
    }

    fun findCoffeeByName(name: String): CoffeeItem? {
        return coffeeItems.find { it.name == name }
    }
}