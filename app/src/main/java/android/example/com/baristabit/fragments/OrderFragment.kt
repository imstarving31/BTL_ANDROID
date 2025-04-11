package android.example.com.baristabit

import android.example.com.baristabit.Order

import android.example.com.baristabit.databinding.ActivityOrderListBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class OrderedListFragment : Fragment() {
    private var _binding: ActivityOrderListBinding? = null
    private val binding get() = _binding!!

    private val sampleOrders = listOf(
        Order("CustomerName", "10:12", listOf("Capuchino (1)", "Black Coffee (1)", "Croissant (2)"), true),
        Order("CustomerName", "10:30", listOf("Black Coffee (1)", "Vietnamese Iced Coffee (2)"), false),
        Order("CustomerName", "10:50", listOf("Croissant (4)", "Vietnamese Iced Coffee (2)"), false)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = OrderAdapter(sampleOrders)
        binding.orderRecyclerView.adapter = adapter
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
