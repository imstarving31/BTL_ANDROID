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
        val coffeeItems = CoffeeData.coffeeItems
        val adapter = CoffeeAdapter(coffeeItems)
        {
                selectedItem ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("name", selectedItem.name)
            intent.putExtra("rating", selectedItem.rating)
            intent.putExtra("desc", selectedItem.description)
            intent.putExtra("image", selectedItem.imageResId)
            intent.putExtra("price",selectedItem.price)
            intent.putExtra("quantity",selectedItem.quantity)
            intent.putExtra("isselected",selectedItem.isSelected)
            orderLauncher.launch(intent)
        }
        orderLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val itemName = result.data?.getStringExtra("name")
                val quantity = result.data!!.getIntExtra("quantity", 0)
                val isSelected = result.data!!.getBooleanExtra("isselected",false)

                // Tìm item tương ứng và cập nhật (giả sử bạn có quantity trong CoffeeItem)
                val updatedItems = coffeeItems.map{
                    if (it.name == itemName) {
                        it.copy(isSelected = isSelected)
                        it.copy(quantity = quantity)
//                        if(it.isSelected){
//                            CartManager.addItem(it)
//                        }else it
                    } else it
                }

                // Cập nhật RecyclerView
                adapter.updateData(updatedItems)
            }
        }

        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)
        binding.homeRecycleView.adapter = adapter
        binding.coffeeLogo.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}