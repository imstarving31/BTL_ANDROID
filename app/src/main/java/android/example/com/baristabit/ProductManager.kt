package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.ActivityProductManagerBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductManager : AppCompatActivity() {
    private lateinit var binding: ActivityProductManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            // Tạo một Intent để chuyển sang AddProductActivity
            val intent = Intent(this, AddProductActivity::class.java)

            // Khởi động AddProductActivity
            startActivity(intent)
        }
    }
}