package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityCustomerNameBinding
import android.example.com.baristabit.databinding.ActivityHomeBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CustomerName : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCustomerNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}