package com.example.qmartapp.addressPage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.data.ClientAddress
import com.example.qmartapp.databinding.FragmentAddressBinding
import com.example.qmartapp.orderPage.CityBottomSheetFragment
import com.example.qmartapp.orderPage.EMPTY

class AddressFragment : Fragment(R.layout.fragment_address) {

    private var selectedIndex = -1
    private var selectedCity = EMPTY

    private val binding: FragmentAddressBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setUI()
    }

    private fun setToolbar() {
        requireActivity().setActionBar(binding.toolbar)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun setUI() = with(binding) {
        val cities = listOf("Алматы", "Каскелен", "Астана")
        chooseCityTextView.setOnClickListener {
            val fragment = CityBottomSheetFragment().apply {
                setCitySelectedListener {
                    selectedIndex = it.first
                    selectedCity = it.second
                    //viewModel.setProductCountry(selectedCountry)
                    updateCityView()
                }
                setCities(
                    selectedIndex,
                    cities
                )
            }
            fragment.show(parentFragmentManager, "Dialog")

        }

        addButton.setOnClickListener {
            val clientAddress =
                ClientAddress(
                    city = selectedCity,
                    streetHouse = streetHouseEditText.text.toString(),
                    apartment = apartmentEditText.text.toString(),
                    entrance = entranceEditText.text.toString(),
                    floor = floorEditText.text.toString()
                )
            val bundle = Bundle()
            bundle.putParcelable("address", clientAddress)
            findNavController().navigate(R.id.action_addressFragment_to_orderFragment, bundle)
        }
    }


    private fun updateCityView() {
        binding.chooseCityTextView.text = if (selectedCity != EMPTY) selectedCity else ""
    }

}