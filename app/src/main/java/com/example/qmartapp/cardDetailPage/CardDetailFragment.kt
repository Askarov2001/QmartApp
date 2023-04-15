package com.example.qmartapp.cardDetailPage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.databinding.FragmentCardDetailBinding

class CardDetailFragment : Fragment(R.layout.fragment_card_detail) {

    private val binding: FragmentCardDetailBinding by viewBinding()
    companion object {
        fun newInstance() = CardDetailFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
    }

    fun setToolbar() {
        requireActivity().setActionBar(binding.toolbar)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun setUI() = with(binding) {
    }
    //private lateinit var viewModel: CardDetailViewModel

}