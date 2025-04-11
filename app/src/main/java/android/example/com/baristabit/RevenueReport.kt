package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityRevenueReportBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RevenueReport : AppCompatActivity() {
    private lateinit var binding: ActivityRevenueReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRevenueReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}