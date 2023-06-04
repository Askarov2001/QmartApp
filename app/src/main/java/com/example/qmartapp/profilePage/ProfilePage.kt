package com.example.qmartapp.profilePage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.MenuActivity
import com.example.qmartapp.R
import com.example.qmartapp.SharedPref
import com.example.qmartapp.SignActivity
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.databinding.FragmentProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfilePage : BaseFragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pathUser = database.child("users")
            .child((requireActivity() as MenuActivity).getValue(SharedPref.UID) ?: "")

        binding.apply {
            edit.setOnClickListener {
                nameEditText.isEnabled = true
                phoneEd.isEnabled = true
            }
            pathUser.child("name").get().addOnCompleteListener {
                nameEditText.setText(it.result.value.toString())
            }
            pathUser.child("phone").get().addOnCompleteListener {
                phoneEd.setText(it.result.value.toString())
            }
            nameEditText.addTextChangedListener {
                pathUser.child("name").setValue(it.toString())
            }
            phoneEd.addTextChangedListener {
                pathUser.child("phone").setValue(it.toString())
            }

            deleteBtn.setOnClickListener {
                val Intent = Intent(requireActivity(), SignActivity ::class.java)
                pathUser.removeValue().addOnSuccessListener {
                    startActivity(Intent)
                }
            }
        }
    }
}