package android.example.com.baristabit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide // Import Glide
import android.example.com.baristabit.databinding.ActivityAddProductBinding
import android.provider.MediaStore

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private var selectedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            if (data != null && data.data != null) {
                selectedImageUri = data.data
                Glide.with(this).load(selectedImageUri).into(binding.imageViewAddPicture) // Sử dụng Glide
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardViewPicture.setOnClickListener {
            openImageChooser()
        }

        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val description = binding.editTextDescription.text.toString().trim()
            val cost = binding.editTextCost.text.toString().trim()

            if (name.isNotEmpty() && description.isNotEmpty() && cost.isNotEmpty() && selectedImageUri != null) {
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("description", description)
                resultIntent.putExtra("cost", cost)
                resultIntent.putExtra("imageUri", selectedImageUri.toString())
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // ... (thông báo lỗi nếu cần)
            }
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }
}