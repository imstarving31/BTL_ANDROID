package android.example.com.baristabit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter2(
    private val orderList: List<Order2>,
    private val onOrderClickListener: OnOrderClickListener
) : RecyclerView.Adapter<OrderAdapter2.OrderViewHolder>() {

    interface OnOrderClickListener {
        fun onOrderClick(order: Order2, position: Int)
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCustomerName: TextView = itemView.findViewById(R.id.txtCustomerName)
        val txtOrderTime: TextView = itemView.findViewById(R.id.txtOrderTime)
        val txtItems: TextView = itemView.findViewById(R.id.txtItems)
        val imgType: ImageView = itemView.findViewById(R.id.imgType)
        val btnTimeDone: TextView = itemView.findViewById(R.id.txttimeDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_2, parent, false)
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
            "takeaway" -> holder.imgType.setImageResource(R.drawable.ic_bag)
            "home" -> holder.imgType.setImageResource(R.drawable.ic_home)
        }

        holder.btnTimeDone.text = "Done Time: ${order.doneTime}"

        // Set click listener for the whole item
        holder.itemView.setOnClickListener {
            onOrderClickListener.onOrderClick(order, position)
        }
    }

    override fun getItemCount(): Int = orderList.size
}
