package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityDeleteBinding
import android.example.com.baristabit.databinding.ActivityHomeBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Delete : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}