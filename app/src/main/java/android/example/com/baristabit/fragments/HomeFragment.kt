package android.example.com.baristabit

import android.app.Activity
import android.content.Intent
import android.example.com.baristabit.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: CoffeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CoffeeAdapter(CoffeeData.coffeeItems) { selectedItem ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("name", selectedItem.name)
            intent.putExtra("rating", selectedItem.rating)
            intent.putExtra("desc", selectedItem.description)
            intent.putExtra("image", selectedItem.imageResId)
            intent.putExtra("price", selectedItem.price)
            intent.putExtra("quantity", selectedItem.quantity)
            intent.putExtra("select", selectedItem.isSelected)
            orderLauncher.launch(intent)
        }

        orderLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val itemName = result.data?.getStringExtra("name")
                val quantity = result.data?.getIntExtra("quantity", 0) ?: 0
                val isSelected = result.data?.getBooleanExtra("select", false) ?: false

                itemName?.let {
                    // Cập nhật dữ liệu trong CoffeeData
                    CoffeeData.updateCoffee(it, quantity, isSelected)

                    // Cập nhật RecyclerView
                    adapter.updateData(CoffeeData.coffeeItems)
                }
            }
        }

        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)
        binding.homeRecycleView.adapter = adapter
        binding.coffeeLogo.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Cập nhật lại danh sách mỗi khi quay lại fragment
        adapter.updateData(CoffeeData.coffeeItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}