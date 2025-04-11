package android.example.com.baristabit

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import android.example.com.baristabit.databinding.ActivityPaymentBinding

class Payment : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gán sự kiện cho từng RadioButton
        binding.rbCreditCard.setOnClickListener {
            binding.rbCreditCard.isChecked = true
            binding.rbTransfer.isChecked = false
            binding.rbCash.isChecked = false
        }

        binding.rbTransfer.setOnClickListener {
            binding.rbCreditCard.isChecked = false
            binding.rbTransfer.isChecked = true
            binding.rbCash.isChecked = false
        }

        binding.rbCash.setOnClickListener {
            binding.rbCreditCard.isChecked = false
            binding.rbTransfer.isChecked = false
            binding.rbCash.isChecked = true
        }
        binding.btnPayNow.setOnClickListener{
            val intent = Intent(this, QRScan::class.java)
            startActivity(intent)
        }

    }
}
