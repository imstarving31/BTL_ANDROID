package android.example.com.baristabit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.model.CoffeeOrder

class OrderAdapter2(
    private val orderList: List<CoffeeOrder>,
    private val onOrderClickListener: OnOrderClickListener
) : RecyclerView.Adapter<OrderAdapter2.OrderViewHolder>() {

    interface OnOrderClickListener {
        fun onOrderClick(order: CoffeeOrder, position: Int)
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCustomerName: TextView = itemView.findViewById(R.id.txtCustomerName)
        val txtOrderTime: TextView = itemView.findViewById(R.id.txtOrderTime)
        val txtItems: TextView = itemView.findViewById(R.id.txtItems)
        val imgType: ImageView = itemView.findViewById(R.id.imgType)
        val btnTimeDone: TextView = itemView.findViewById(R.id.btntimeDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_card, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]

        holder.txtCustomerName.text = order.customerName
        holder.txtOrderTime.text = "Order Time: ${order.orderTime}"

        // Set the items text
        val itemsText = StringBuilder()
        for (item in order.items) {
            itemsText.append("${item.name} (${item.quantity})\n")
        }
        holder.txtItems.text = itemsText.toString().trim()

        // Set the order type icon
        when (order.orderType) {
            "takeaway" -> holder.imgType.setImageResource(R.drawable.ic_takeaway)
            "delivery" -> holder.imgType.setImageResource(R.drawable.ic_delivery)
            "home" -> holder.imgType.setImageResource(R.drawable.ic_home)
            else -> holder.imgType.setImageResource(R.drawable.ic_cafe)
        }

        holder.btnTimeDone.text = "Done Time: ${order.doneTime}"

        // Set click listener for the whole item
        holder.itemView.setOnClickListener {
            onOrderClickListener.onOrderClick(order, position)
        }
    }

    override fun getItemCount(): Int = orderList.size
}
