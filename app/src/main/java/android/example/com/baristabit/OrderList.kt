package android.example.com.baristabit

import android.example.com.baristabit.Order
import android.content.Intent
import android.example.com.baristabit.databinding.ActivityOrderListBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class OrderList : AppCompatActivity() {
    private lateinit var binding: ActivityOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Dữ liệu mẫu
        val orders = listOf(
            Order(
                customerName = "CustomerName",
                orderTime = "10:12",
                items = listOf("Capuchino (1)", "Black Coffee (1)", "Vietnamese Ice Coffee (2)", "Croissant (1)"),
                isTakeAway = true
            ),
            Order(
                customerName = "CustomerName",
                orderTime = "10:30",
                items = listOf("Black Coffee (1)", "Vietnamese Ice Coffee (2)"),
                isTakeAway = false
            ),
            Order(
                customerName = "CustomerName",
                orderTime = "10:50",
                items = listOf("Croissant (4)", "Vietnamese Ice Coffee (2)"),
                isTakeAway = false
            )
        )

        // Setup RecyclerView
        val adapter = OrderAdapter(orders)
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.orderRecyclerView.adapter = adapter

        // Nút menu
        binding.btnMenu.setOnClickListener {
            val intent = Intent(this, DoneList::class.java)
            startActivity(intent)
        }
    }
}
