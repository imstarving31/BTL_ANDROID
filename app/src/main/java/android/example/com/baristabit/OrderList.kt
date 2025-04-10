package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.ActivityMainBinding
import android.example.com.baristabit.databinding.ActivityOrderListBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderList : AppCompatActivity() {
    private lateinit var binding: ActivityOrderListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMenu.setOnClickListener {
            // Chuyển sang DoneList
            val intent = Intent(this, DoneList::class.java)
            startActivity(intent)
        }
    }
}