package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.ActivityProductManagerBinding
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductManager : AppCompatActivity(), ProductAdapter.OnItemClickListener {
    private lateinit var binding: ActivityProductManagerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductAdapter.ProductItem>() // Hoặc List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvProductList // Lấy tham chiếu RecyclerView từ binding
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Tạo dữ liệu mẫu (thay thế bằng dữ liệu thực tế của bạn)
        productList.add(ProductAdapter.ProductItem(R.drawable.croissant, "Croissant"))
        productList.add(ProductAdapter.ProductItem(R.drawable.croissant, "Another Product"))
        productList.add(ProductAdapter.ProductItem(R.drawable.croissant, "Yet Another Product"))
        // Thêm nhiều sản phẩm khác

        adapter = ProductAdapter(productList)
        adapter.setOnItemClickListener(this) // Thiết lập listener cho nút sửa/xóa
        recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }
        binding.btnRevenue.setOnClickListener {
            val intent = Intent(this, RevenueReport::class.java)
            startActivity(intent)
        }
    }



    override fun onEditClick(position: Int) {
        val clickedItem = productList[position]
        Toast.makeText(this, "Sửa: ${clickedItem.productName} tại vị trí $position", Toast.LENGTH_SHORT).show()
        // Xử lý logic sửa tại đây
    }

    override fun onDeleteClick(position: Int) {
        val clickedItem = productList[position]
        Toast.makeText(this, "Xóa: ${clickedItem.productName} tại vị trí $position", Toast.LENGTH_SHORT).show()
        // Xử lý logic xóa tại đây
        productList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}