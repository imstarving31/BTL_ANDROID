package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.ActivityMainBinding
import android.example.com.baristabit.databinding.LoginBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login1.setOnClickListener(){
            val intent = Intent(this, OrderList::class.java)
            startActivity(intent)
        }

        binding.btnlogin2.setOnClickListener {
            // Tạo một Intent để chuyển sang ProductManager Activity
            val intent = Intent(this, ProductManager::class.java)

            // Khởi động ProductManager Activity
            startActivity(intent)

            // Optional: Đóng LoginActivity sau khi chuyển (tùy thuộc vào luồng ứng dụng)
            // finish()
        }
    }

}
