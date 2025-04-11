package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityHomeBinding
import android.example.com.baristabit.databinding.ActivityPasswordChangeBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PasswordChange : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPasswordChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}