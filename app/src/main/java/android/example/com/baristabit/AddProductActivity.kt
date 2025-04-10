package android.example.com.baristabit

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.example.com.baristabit.databinding.ActivityAddProductBinding
import androidx.cardview.widget.CardView // Import CardView

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var imageViewAddPicture: ImageView
    private lateinit var cardViewPicture: CardView // Sử dụng CardView

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null

    // Activity Result API để xử lý kết quả chọn ảnh (khuyến nghị)
    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null && data.data != null) {
                selectedImageUri = data.data
                imageViewAddPicture.setImageURI(selectedImageUri)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageViewAddPicture = binding.imageViewAddPicture
        cardViewPicture = binding.cardViewPicture // Lấy tham chiếu đến CardView

        binding.cardViewPicture.setOnClickListener { // Đặt OnClickListener cho CardView
            openImageChooser()
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }

    // (Phương pháp cũ - bạn có thể sử dụng Activity Result API như trên)
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            imageViewAddPicture.setImageURI(selectedImageUri)
        }
    }
    */
}