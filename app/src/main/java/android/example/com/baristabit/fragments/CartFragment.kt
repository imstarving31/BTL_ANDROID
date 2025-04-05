package android.example.com.baristabit

import android.example.com.baristabit.databinding.FragmentCartBinding
import android.example.com.baristabit.models.CartItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartItems = listOf(
            CartItem(
                "Cappuccino",
                2.0,
                R.drawable.cappuccino,
                1
            ),
            CartItem(
                "Black Coffee",
                2.0,
                R.drawable.black_coffee,
                1
            ),
            CartItem(
                "Vietnam Ice Coffee",
                2.36,
                R.drawable.vietnam_ice_coffee,
                2
            ),
            CartItem(
                "Croissant",
                3.6,
                R.drawable.croissant,
                1
            )
        )

        val adapter = CartAdapter(cartItems)
        binding.cartRecycleView.layoutManager = LinearLayoutManager(context)
        binding.cartRecycleView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}