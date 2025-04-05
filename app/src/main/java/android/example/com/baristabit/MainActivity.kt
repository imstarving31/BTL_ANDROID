package android.example.com.baristabit

import android.example.com.baristabit.databinding.ActivityMainBinding
import android.example.com.baristabit.fragments.CartFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_cart -> {
                    loadFragment(CartFragment())
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}