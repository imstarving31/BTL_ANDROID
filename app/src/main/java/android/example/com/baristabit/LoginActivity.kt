package android.example.com.baristabit

import android.content.Intent
import android.example.com.baristabit.database_helper.LoginHelper
import android.example.com.baristabit.databinding.ActivityAddProductBinding
import android.example.com.baristabit.databinding.LoginBinding
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private lateinit var binding: LoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var loginHelper: LoginHelper
    private lateinit var btnBack: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        edtEmail = findViewById(R.id.username_edittext_id)
        edtPassword = findViewById(R.id.password_edittext_id)
        btnLogin = findViewById(R.id.btnLogin)
        btnBack = findViewById(R.id.btnBack)

        loginHelper = LoginHelper(this)
        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ email và mật khẩu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginHelper.loginUser(email, password, object : LoginHelper.OnUserInfoRetrievedListener {
                override fun onUserInfoRetrieved(userInfo: Map<String, Any>) {
                    val role = userInfo["role"].toString()
                    val intent = if (role == "manager") {
                        Intent(this@LoginActivity, ProductManager::class.java)
                    } else {
                        Intent(this@LoginActivity, OrderList::class.java)
                    }
                    startActivity(intent)
                    finish()
                }
            })
        }
    }
}
