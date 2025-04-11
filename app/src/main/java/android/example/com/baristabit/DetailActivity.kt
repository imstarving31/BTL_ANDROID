package android.example.com.baristabit

import android.app.Activity
import android.content.Intent
import android.example.com.baristabit.databinding.ActivityDetailBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var basePrice: Double = 0.0
    private lateinit var coffeeItem: CoffeeItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coffeeItem = intent.getSerializableExtra("item") as CoffeeItem
        var quantity = coffeeItem.quantity
        val basePrice = coffeeItem.price / quantity

        updateUI(quantity, basePrice)

        binding.btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateUI(quantity, basePrice)
            }
        }

        binding.btnPlus.setOnClickListener {
            quantity++
            updateUI(quantity, basePrice)
        }

        binding.btnOrder.setOnClickListener {
            coffeeItem.quantity = quantity
            coffeeItem.isSelected = true
            // Cập nhật CoffeeStorage
            CoffeeStorage.updateItem(this, coffeeItem)

            // Thêm vào giỏ hàng
            CartManager.addItem(coffeeItem)

            val resultIntent = Intent()
            resultIntent.putExtra("updatedItem", coffeeItem)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    private fun updateUI(quantity: Int, basePrice: Double) {
        binding.txtTitle.text = coffeeItem.name
        binding.txtDescription.text = coffeeItem.description
        binding.imgProduct.setImageResource(coffeeItem.imageResId)
        binding.txtRating.text = "${coffeeItem.rating}"
        binding.txtQuantity.text = "$quantity"
        binding.txtPrice.text = "$${String.format("%.2f", quantity * basePrice)}"
    }
    }