package com.example.qmartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var res_Email: EditText
    private lateinit var res_Password: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


        res_Email = findViewById(R.id.reset_email)
        res_Password = findViewById(R.id.reset_password)

        auth = FirebaseAuth.getInstance()


        res_Password.setOnClickListener {
            val sPassword = res_Email.text.toString()
            auth.sendPasswordResetEmail(sPassword)
                .addOnSuccessListener {
                    Toast.makeText(this, "Проверьте почту!",Toast.LENGTH_SHORT).show()
                }

                .addOnFailureListener {
                    Toast.makeText(this, it.toString(),Toast.LENGTH_SHORT).show()
                }

        }


    }
}