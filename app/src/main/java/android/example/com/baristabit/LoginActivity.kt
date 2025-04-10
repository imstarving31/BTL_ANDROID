package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.ActivityMainBinding
import android.example.com.baristabit.databinding.LoginBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext

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
    }
}
