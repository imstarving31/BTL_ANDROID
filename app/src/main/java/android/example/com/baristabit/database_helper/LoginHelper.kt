package android.example.com.baristabit.database_helper

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginHelper(private val context: Context) {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    interface OnUserInfoRetrievedListener {
        fun onUserInfoRetrieved(userInfo: Map<String, Any>)
    }

    fun loginUser(email: String, password: String, listener: OnUserInfoRetrievedListener) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = mAuth.currentUser?.uid
                    if (userId != null) {
                        db.collection("users")
                            .whereEqualTo("email", email)
                            .get()
                            .addOnSuccessListener { documents ->
                                if (!documents.isEmpty) {
                                    val doc = documents.documents[0]
                                    val userInfo = mapOf(
                                        "email" to (doc.getString("email") ?: ""),
                                        "role" to (doc.getString("role") ?: "")
                                    )
                                    listener.onUserInfoRetrieved(userInfo)
                                } else {
                                    showAlertDialog("Lỗi", "Không tìm thấy thông tin người dùng")
                                }
                            }

                    } else {
                        showAlertDialog("Lỗi", "Không tìm thấy UID người dùng")
                    }
                } else {
                    showAlertDialog("Đăng nhập thất bại", "Lỗi: ${task.exception?.message}")
                }
            }
    }

    private fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
