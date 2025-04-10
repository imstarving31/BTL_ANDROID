package android.example.com.baristabit

import android.app.Activity
import android.content.Intent
import android.example.com.baristabit.databinding.ActivityDetailBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var basePrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name") ?: ""
        val rating = intent.getDoubleExtra("rating", 0.0)
        val desc = intent.getStringExtra("desc") ?: ""
        val image = intent.getIntExtra("image", 0)
        val initialPrice = intent.getDoubleExtra("price", 0.0)
        var quantity = intent.getIntExtra("quantity", 1)
        var isSelected = intent.getBooleanExtra("select", false)

        // Tìm sản phẩm trong danh sách hoặc tạo mới nếu không tìm thấy
        val coffeeItem = CoffeeData.findCoffeeByName(name)

        // Sử dụng dữ liệu từ CoffeeData nếu có
        if (coffeeItem != null) {
            quantity = coffeeItem.quantity
            isSelected = coffeeItem.isSelected
        }

        // Tính giá cơ bản (giá cho 1 đơn vị)
        basePrice = if (quantity > 0) initialPrice / quantity else initialPrice
        var currentPrice = basePrice * quantity

        binding.imgProduct.setImageResource(image)
        binding.txtTitle.text = name
        binding.txtRating.text = "$rating"
        binding.txtDescription.text = desc
        binding.txtPrice.text = "$${String.format("%.2f", currentPrice)}"
        binding.txtQuantity.text = "$quantity"

        binding.btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity -= 1
                currentPrice = basePrice * quantity
                binding.txtPrice.text = "$${String.format("%.2f", currentPrice)}"
                binding.txtQuantity.text = "$quantity"
            }
        }

        binding.btnPlus.setOnClickListener {
            quantity += 1
            currentPrice = basePrice * quantity
            binding.txtPrice.text = "$${String.format("%.2f", currentPrice)}"
            binding.txtQuantity.text = "$quantity"
        }

        binding.btnOrder.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("quantity", quantity)
            isSelected = true
            resultIntent.putExtra("select", isSelected)

            // Cập nhật dữ liệu trong CoffeeData và CartManager
            CoffeeData.updateCoffee(name, quantity, isSelected)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}