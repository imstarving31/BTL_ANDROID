package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.ActivityProductManagerBinding
import android.example.com.baristabit.models.ProductItem
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductManager : AppCompatActivity(), ProductAdapter.OnItemClickListener {
    private lateinit var binding: ActivityProductManagerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductItem>()
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val newItem = result.data?.getSerializableExtra("newItem") as? CoffeeItem
            newItem?.let {
                val list = CoffeeStorage.getCoffeeList(this)
                list.add(it)
                CoffeeStorage.saveList(this, list)
                Toast.makeText(this, "Đã thêm sản phẩm mới", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvProductList
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dữ liệu mẫu
        val imageUri = Uri.parse("android.resource://${packageName}/${R.drawable.croissant}").toString()

        productList.add(ProductItem(imageUri, "Croissant", "Bánh sừng bò Pháp", "20000"))
        productList.add(ProductItem(imageUri, "Another Product", "Mô tả khác", "25000"))
        productList.add(ProductItem(imageUri, "Yet Another Product", "Mô tả nữa", "30000"))

        adapter = ProductAdapter(productList)
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            launcher.launch(intent)
        }
        binding.btnRevenue.setOnClickListener {
            val intent = Intent(this, RevenueReport::class.java)
            startActivity(intent)
        }
        binding.btnExport.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



    override fun onEditClick(position: Int) {
        val clickedItem = productList[position]
        val intent = Intent(this, AddProductActivity::class.java)
        intent.putExtra("product", clickedItem)
        intent.putExtra("edit_mode", true)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    override fun onDeleteClick(position: Int) {
        val clickedItem = productList[position]
        Toast.makeText(this, "Xóa: ${clickedItem.productName} tại vị trí $position", Toast.LENGTH_SHORT).show()
        productList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
