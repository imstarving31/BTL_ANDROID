package android.example.com.baristabit

import android.content.Intent
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
                4.9,
                "an Italian classic, featuring a perfect harmony of rich espresso, steamed milk, and a velvety froth, often dusted with cocoa or cinnamon. This iconic beverage reflects a rich coffee heritage, offering a warm and creamy delight that’s an ideal companion for a cozy café experience.",
                R.drawable.cappuccino,
                false,
                2.0
            ),
            CoffeeItem(
                "Black Coffee",
                4.8,
                "Black coffee is a pure delight—bold, rich, and invigorating! Its deep aroma and smooth bitterness awaken the senses, while subtle hints of chocolate, nuts, or fruit make every sip an exciting experience. There’s nothing like the comforting warmth and intense flavor of a well-brewed cup to start the day!",
                R.drawable.black_coffee,
                false,
                2.0
            ),
            CoffeeItem(
                "Vietnam Ice Coffee",
                5.0,
                "Vietnamese Iced Coffee – a Vietnamese specialty, blending the rich, bold flavor of traditional phin-brewed coffee with creamy sweetened condensed milk and refreshing ice, delivering a unique and invigorating taste. This drink not only reflects Vietnam’s long-standing coffee culture but also embodies culinary pride, making it the perfect companion for a cozy café experience.",
                R.drawable.vietnam_ice_coffee,
                false,
                1.18
            ),
            CoffeeItem(
                "Croissant",
                4.6,
                "Croissant – a classic French pastry, renowned for its buttery, flaky layers and golden, crescent-shaped perfection. Freshly baked with a crisp exterior and a soft, airy interior, this timeless treat pairs beautifully with coffee, offering a delightful balance of richness and lightness for a cozy café moment.",
                R.drawable.croissant,
                false,
                3.6
            )
        )

        val adapter = CoffeeAdapter(coffeeItems){
                selectedItem ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("name", selectedItem.name)
            intent.putExtra("rating", selectedItem.rating)
            intent.putExtra("desc", selectedItem.description)
            intent.putExtra("image", selectedItem.imageResId)
            intent.putExtra("isfav", selectedItem.isFavorite)
            intent.putExtra("price",selectedItem.price)
            startActivity(intent)
        }
        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)
        binding.homeRecycleView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}