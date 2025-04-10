package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityAddProductBinding
import android.example.com.baristabit.databinding.ActivityMainBinding
import android.example.com.baristabit.databinding.FragmentProductManagerBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: FragmentProductManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProductManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener()
    }
}