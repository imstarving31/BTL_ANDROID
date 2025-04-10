package android.example.com.baristabit
import android.example.com.baristabit.CoffeeItem
import android.example.com.baristabit.databinding.ItemCoffeeBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CoffeeAdapter(
    private var coffeeItems: List<CoffeeItem>,
    private val onItemClick:(CoffeeItem) -> Unit) :
    RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    class CoffeeViewHolder(val binding: ItemCoffeeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val binding = ItemCoffeeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoffeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val item = coffeeItems[position]
        with(holder.binding) {
            coffeeImage.setImageResource(item.imageResId)
            coffeeTitle.text = item.name
            coffeeRating.text = item.rating.toString()
            coffeeDescription.text = item.description

//            favoriteButton.setImageResource(
//                if (item.isFavorite) R.drawable.ic_favorite_filled
//                else R.drawable.ic_favorite_outline
//            )

            favoriteButton.setOnClickListener {
                item.isFavorite = !item.isFavorite
                favoriteButton.setImageResource(
                    if (item.isFavorite) R.drawable.ic_favorite_filled
                    else R.drawable.ic_favorite_outline
                )
            }

        }
        holder.itemView.setOnClickListener{
                onItemClick(item)
        }
    }

    override fun getItemCount() = coffeeItems.size
    fun updateData(newItems: List<CoffeeItem>) {
        coffeeItems = newItems
        notifyDataSetChanged()
    }

}