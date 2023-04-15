package com.example.qmartapp

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.databinding.FragmentProfileBinding

class ProfileFr : BaseFragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener {
                navController?.popBackStack() ?: parentFragmentManager.popBackStack()
            }
            toolbar.title = "Привет, ${getValue(SharedPref.NAME)}"
            nameEd.setText(getValue(SharedPref.NAME))
            phoneEd.setText(getValue(SharedPref.PHONE))
        }
    }

}