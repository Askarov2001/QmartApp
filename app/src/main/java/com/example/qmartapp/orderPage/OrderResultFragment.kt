package com.example.qmartapp.orderPage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.databinding.FragmentOrderResultBinding

class OrderResultFragment : Fragment(R.layout.fragment_order_result) {

    private val binding: FragmentOrderResultBinding by viewBinding()

    companion object {
        fun newInstance() = OrderResultFragment()
    }

    //private lateinit var viewModel: OrderResultViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToCatalog.setOnClickListener {
            findNavController().navigate(R.id.action_orderResultFragment_to_homeScreen)
        }
    }

}