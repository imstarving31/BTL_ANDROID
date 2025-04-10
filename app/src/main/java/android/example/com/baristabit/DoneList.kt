package android.example.com.baristabit

import Order
import android.content.Intent
import android.example.com.baristabit.databinding.ActivityAddProductBinding
import android.example.com.baristabit.databinding.ActivityDoneListBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager

class DoneList : AppCompatActivity() {
    private lateinit var binding: ActivityDoneListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneListBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // Nút back
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, OrderList::class.java)
            startActivity(intent)
        }
    }
}