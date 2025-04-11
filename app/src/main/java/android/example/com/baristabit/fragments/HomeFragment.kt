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
    private lateinit var coffeeAdapter: CoffeeAdapter
    private var coffeeList = mutableListOf<CoffeeItem>()

    private val detailLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedItem = result.data?.getSerializableExtra("updatedItem") as? CoffeeItem
            updatedItem?.let {
                CoffeeStorage.updateItem(requireContext(), it)
                coffeeList = CoffeeStorage.getCoffeeList(requireContext())
                coffeeAdapter.updateData(coffeeList)
            }
        }
    }
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

        coffeeList = CoffeeStorage.getCoffeeList(requireContext())
        coffeeAdapter = CoffeeAdapter(coffeeList) { item ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("item", item)
            detailLauncher.launch(intent)
        }



        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)
        binding.homeRecycleView.adapter = coffeeAdapter
        binding.coffeeLogo.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Cập nhật lại danh sách mỗi khi quay lại fragment
        coffeeAdapter.updateData(CoffeeStorage.getCoffeeList(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}