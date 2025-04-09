package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityDetailBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val rating = intent.getDoubleExtra("rating", 0.0)
        val desc = intent.getStringExtra("desc")
        val image = intent.getIntExtra("image", 0)
        var price = intent.getDoubleExtra("price",0.0)
        var quantity = 1
        binding.imgProduct.setImageResource(image)
        binding.txtTitle.text = name
        binding.txtRating.text = "$rating"
        binding.txtDescription.text = desc
        binding.txtPrice.text = "$$price"
        binding.txtQuantity.text = "$quantity"
        binding.btnMinus.setOnClickListener {
            if (quantity > 1) {
                price -= (price / quantity)
                binding.txtPrice.text = "$$price"
                quantity -= 1
                binding.txtQuantity.text = "$quantity"
            }
        }
        binding.btnPlus.setOnClickListener{
            price += (price / quantity)
            binding.txtPrice.text = "$$price"
            quantity += 1
            binding.txtQuantity.text = "$quantity"
        }
    }
}
