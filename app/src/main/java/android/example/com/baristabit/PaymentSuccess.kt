package android.example.com.baristabit

import android.example.com.baristabit.databinding.PaymentSuccessBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentSuccess : AppCompatActivity() {
    private lateinit var binding: PaymentSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = PaymentSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}