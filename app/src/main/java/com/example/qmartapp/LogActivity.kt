package com.example.qmartapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LogActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        val sharedPref: SharedPreferences = this@LogActivity.getPreferences(MODE_PRIVATE)
        auth = FirebaseAuth.getInstance()

        val log_Email: EditText = findViewById(R.id.log_Email)
        val log_Password: EditText = findViewById(R.id.log_Password)
        val log_PasswordLayout: TextInputLayout = findViewById(R.id.log_PasswordLayout)
        val loginbtn: Button = findViewById(R.id.loginbtn)

        val forgetPass: TextView = findViewById(R.id.forgetPass)
        val signNow: TextView = findViewById(R.id.signNow)

        signNow.setOnClickListener {
            val Intent = Intent(this, SignActivity::class.java)
            startActivity(Intent)
        }

        forgetPass.setOnClickListener {
            val Intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(Intent)
        }

        loginbtn.setOnClickListener {
            log_PasswordLayout.isPasswordVisibilityToggleEnabled = true

            val email = log_Email.text.toString()
            val password = log_Password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    log_Email.error = "Введите электронную почту"
                }
                if (password.isEmpty()) {
                    log_Password.error = "Введите пароль"
                    log_PasswordLayout.isPasswordVisibilityToggleEnabled = false
                }
                Toast.makeText(this, "Введите информацию", Toast.LENGTH_SHORT).show()
            } else if (!email.matches(emailPattern.toRegex())) {
                log_Email.error = "Введите почту правильно"
                Toast.makeText(this, "Введите почту правильно", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                log_Password.error = "Должно быть более 6 символов"
                Toast.makeText(this, "Должно быть более 6 символов", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val result = it.result.user?.zzb()
                        val token = it.result.additionalUserInfo?.providerId
                        val ss = it.result.additionalUserInfo.toString()
                        val Intent = Intent(this, MenuActivity::class.java)
                        startActivity(Intent)
                    } else {
                        Toast.makeText(this, "Ошибка! Повторите еще раз!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }

    }

}