package android.example.com.baristabit

import android.example.com.baristabit.CoffeeAdapter.CoffeeViewHolder
import android.example.com.baristabit.databinding.CartItemBinding
import android.example.com.baristabit.databinding.ItemCoffeeBinding
import android.example.com.baristabit.models.CartItem
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val items: List<CartItem>,
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
        val item = items[position]

        with(holder.binding) {
            itemImage.setImageResource(item.imageResource)
            itemName.text = item.name
            itemPrice.text = "$${item.price}"
            quantityText.text = item.quantity.toString()

            decreaseButton.setOnClickListener {
                item.price -=(item.price/item.quantity)
                itemPrice.text = "$${item.price}"
                if (item.quantity > 0) {
                    item.quantity -= 1
                    quantityText.text = item.quantity.toString()
                }

            }

            increaseButton.setOnClickListener {
                item.price +=(item.price/item.quantity)
                itemPrice.text = "$${item.price}"
                item.quantity += 1
                quantityText.text = item.quantity.toString()

            }
        }


    }

    override fun getItemCount() = items.size
}