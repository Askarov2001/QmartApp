package com.example.qmartapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val sp: SharedPref by lazy {
        SharedPref().apply {
            init(this@SignActivity)
        }
    }
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val sign_Name: EditText = findViewById(R.id.sign_Name)
        val sign_Phone: EditText = findViewById(R.id.sign_Phone)
        val sign_Email: EditText = findViewById(R.id.sign_Email)
        val sign_Password: EditText = findViewById(R.id.sign_Password)
        val sign_PasswordLayout: TextInputLayout = findViewById(R.id.sign_PasswordLayout)
        val signup_btn: Button = findViewById(R.id.signup_btn)

        val logNow: TextView = findViewById(R.id.logNow)

        logNow.setOnClickListener {
            val Intent = Intent(this, LogActivity::class.java)
            startActivity(Intent)
        }

        signup_btn.setOnClickListener {
            val name = sign_Name.text.toString()
            val phone = sign_Phone.text.toString()
            val email = sign_Email.text.toString()
            val password = sign_Password.text.toString()

            sign_PasswordLayout.isPasswordVisibilityToggleEnabled = true

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                if (name.isEmpty()) {
                    sign_Name.error = "Введите Имя"
                }
                if (phone.isEmpty()) {
                    sign_Phone.error = "Введите Телефон"
                }
                if (email.isEmpty()) {
                    sign_Email.error = "Введите Электронную Почту"
                }
                if (password.isEmpty()) {
                    sign_Password.error = "Введите Пароль"
                    sign_PasswordLayout.isPasswordVisibilityToggleEnabled = false
                }
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show()

            } else if (!email.matches(emailPattern.toRegex())) {
                sign_Email.error = "Введите почту правильно"
                Toast.makeText(this, "Введите почту правильно", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                sign_Password.error = "Должно быть более 6 символов"
                Toast.makeText(this, "Должно быть более 6 символов", Toast.LENGTH_SHORT).show()
            } else if (phone.length < 11) {
                sign_Password.error = "Введите правильный номер телефона"
                Toast.makeText(this, "Введите правильный номер телефона", Toast.LENGTH_SHORT).show()
            } else {
                sp.addProperty(SharedPref.NAME, name)
                sp.addProperty(SharedPref.PHONE, phone)
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val databaseRef =
                            database.reference.child("users").child(auth.currentUser!!.uid)
                        val users: Users =
                            Users(name, phone, email, password, auth.currentUser!!.uid)



                        databaseRef.setValue(users).addOnCompleteListener {
                            if (it.isSuccessful) {
                                val Intent = Intent(this, LogActivity::class.java)
                                startActivity(Intent)


                            } else {
                                Toast.makeText(
                                    this,
                                    "Ошибка! Повторите еще раз!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Ошибка! Повторите еще раз!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }


    }
}