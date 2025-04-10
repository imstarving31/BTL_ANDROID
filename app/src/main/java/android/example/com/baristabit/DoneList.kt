package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityAddProductBinding
import android.example.com.baristabit.databinding.ActivityDoneListBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DoneList : AppCompatActivity() {
    private lateinit var binding: ActivityDoneListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}