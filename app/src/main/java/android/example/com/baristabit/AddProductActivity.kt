package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityAddProductBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.btnAdd.setOnClickListener{
//            // Tạo một Intent để chuyển sang Activity đích (AddNewProductActivity)
//            val intent = Intent(this, AddNewProductActivity::class.java)
//
//            // Khởi động Activity mới
//            startActivity(intent)
//        }
    }
}