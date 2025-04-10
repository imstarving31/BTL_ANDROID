package android.example.com.baristabit

import android.example.com.baristabit.databinding.CartItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var cartItems: MutableList<CoffeeItem>,
    private val onQuantityChanged: (CoffeeItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]

        with(holder.binding) {
            itemImage.setImageResource(item.imageResId)
            itemName.text = item.name
            itemPrice.text = "$${String.format("%.2f", item.price)}"
            quantityText.text = "${item.quantity}"

            decreaseButton.setOnClickListener {
                if (item.quantity > 1) {
                    onQuantityChanged(item, item.quantity - 1)
                    itemPrice.text = "$${String.format("%.2f", item.price)}"
                }
            }

            increaseButton.setOnClickListener {
                onQuantityChanged(item, item.quantity + 1)
                itemPrice.text = "$${String.format("%.2f", item.price)}"
            }
        }
    }

    override fun getItemCount() = cartItems.size

    fun updateItems(newItems: List<CoffeeItem>) {
        cartItems.clear()
        cartItems.addAll(newItems)
        notifyDataSetChanged()
    }
}