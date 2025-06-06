package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.QrScanBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QRScan : AppCompatActivity() {
    private lateinit var binding: QrScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = QrScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish() // Đóng QRScan nếu không cần quay lại
        }
    }
}