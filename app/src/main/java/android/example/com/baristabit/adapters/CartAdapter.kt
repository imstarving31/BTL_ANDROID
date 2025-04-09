package android.example.com.baristabit

import android.example.com.baristabit.databinding.CartItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var cardItems: List<CoffeeItem>,
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
        val item = cardItems[position]

        with(holder.binding) {
            itemImage.setImageResource(item.imageResId)
            itemName.text = item.name
            itemPrice.text = "$${String.format("%.2f", item.price)}"
            quantityText.text = "${item.quantity}"

            decreaseButton.setOnClickListener {
                if (item.quantity > 1) {
                    item.price -=(item.price/item.quantity)
                    itemPrice.text = "$${item.price}"
                    onQuantityChanged(item, item.quantity - 1)
                    quantityText.text = "${item.quantity}"
                }

            }

            increaseButton.setOnClickListener {
                item.price +=(item.price/item.quantity)
                itemPrice.text = "$${item.price}"
                onQuantityChanged(item, item.quantity + 1)
                quantityText.text = "${item.quantity}"

            }
        }


    }

    override fun getItemCount() = cardItems.size
    fun updateItems(newItems: List<CoffeeItem>) {
        cardItems = newItems
        notifyDataSetChanged()
    }
}