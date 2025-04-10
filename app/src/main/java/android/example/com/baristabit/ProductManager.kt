package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityAddProductBinding
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
        binding.btnAdd.setOnClickListener()
    }
}