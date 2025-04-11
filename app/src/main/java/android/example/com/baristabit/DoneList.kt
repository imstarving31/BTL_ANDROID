package android.example.com.baristabit
<<<<<<< Updated upstream

import android.example.com.baristabit.Order
=======
//
>>>>>>> Stashed changes
import android.content.Intent
import android.example.com.baristabit.databinding.ActivityDoneListBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class DoneList : AppCompatActivity() {
    private lateinit var binding: ActivityDoneListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orders = listOf(
            Order2(
                customerName = "CustomerName",
                orderTime = "10:12",
                items = listOf(
                    OrderItem("Capuchino", 1),
                    OrderItem("Black Coffee", 1),
                    OrderItem("Vietnamese Ice Coffee", 2),
                    OrderItem("Croissant", 1)
                ),
                orderType = "takeaway",
                doneTime = "10:25"
            ),
            Order2(
                customerName = "CustomerName",
                orderTime = "10:30",
                items = listOf(
                    OrderItem("Black Coffee", 1),
                    OrderItem("Vietnamese Ice Coffee", 2)
                ),
                orderType = "home",
                doneTime = "10:40"
            ),
            Order2(
                customerName = "CustomerName",
                orderTime = "10:50",
                items = listOf(
                    OrderItem("Croissant", 4),
                    OrderItem("Vietnamese Ice Coffee", 2)
                ),
                orderType = "home",
                doneTime = "11:00"
            )
        )


        // Setup RecyclerView
        val adapter = OrderAdapter2(orders)
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.orderRecyclerView.adapter = adapter

        // Nút back
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, OrderList::class.java)
            startActivity(intent)
        }
    }
}