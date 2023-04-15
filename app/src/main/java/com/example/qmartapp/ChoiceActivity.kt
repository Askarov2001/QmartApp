package com.example.qmartapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        val signBtn = findViewById<Button>(R.id.button)
//        val logBtn = findViewById<Button>(R.id.button2)

        signBtn.setOnClickListener {
            val Intent = Intent(this, SignActivity ::class.java)
            startActivity(Intent)
        }

    }
}