package android.example.com.baristabit

import android.example.com.baristabit.Order
import android.example.com.baristabit.databinding.ItemOrderBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orders: List<Order>) :  // ← sửa ở đây
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        with(holder.binding) {
            txtCustomerName.text = order.customerName
            txtOrderTime.text = "${order.orderTime}"
            txtItems.text = order.items.joinToString("\n")
            imgType.setImageResource(
                if (order.isTakeAway) R.drawable.ic_bag else R.drawable.ic_home
            )
        }
    }

    override fun getItemCount() = orders.size
}
