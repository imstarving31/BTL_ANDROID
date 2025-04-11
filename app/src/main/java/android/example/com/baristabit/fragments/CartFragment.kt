package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.databinding.FragmentCartBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class CartFragment : Fragment() {
    private lateinit var cartAdapter: CartAdapter
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

        cartAdapter = CartAdapter(CartManager.getSelectedItems().toMutableList()) { item, newQuantity ->
            CartManager.updateQuantity(item, newQuantity)
            updateTotal()
            cartAdapter.updateItems(CartManager.getSelectedItems())
        }

        binding.cartRecycleView.layoutManager = LinearLayoutManager(context)
        binding.cartRecycleView.adapter = cartAdapter
        binding.payButton.setOnClickListener{
            val intent = Intent(requireContext(), Payment::class.java)
            startActivity(intent)
        }
        updateTotal()
    }

    private fun updateTotal() {
        val total = CartManager.getTotal()
        binding.totalPriceText.text = "Total: $${String.format("%.2f", total)}"
    }

    override fun onResume() {
        super.onResume()
        // Cập nhật lại dữ liệu mỗi khi quay lại giỏ hàng
        cartAdapter.updateItems(CartManager.getSelectedItems())
        updateTotal()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}