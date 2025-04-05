package android.example.com.baristabit

import android.example.com.baristabit.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        val coffeeItems = listOf(
            CoffeeItem(
                "Cappuccino",
                4.9f,
                "A combination of espresso, steamed milk, and a thick layer of milk foam, often sprinkled with cocoa powder or cinnamon.",
                R.drawable.cappuccino
            ),
            CoffeeItem(
                "Black Coffee",
                4.8f,
                "Black coffee is bold, rich, and invigorating, with a pure aroma that can be enjoyed plain or with sugar, chocolate, nuts, or fruit make each sip exciting, perfect for starting the day.",
                R.drawable.black_coffee
            ),
            CoffeeItem(
                "Vietnam Ice Coffee",
                5.0f,
                "Traditional drip coffee combined with sweetened milk and ice, a signature from Vietnam.",
                R.drawable.vietnam_ice_coffee
            ),
            CoffeeItem(
                "Croissant",
                4.6f,
                "A classic French pastry with buttery, flaky layers and a golden, crisp exterior. Soft inside and wonderfully versatile for a delightful coffee delight.",
                R.drawable.croissant
            )
        )

        val adapter = CoffeeAdapter(coffeeItems)
        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)
        binding.homeRecycleView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}