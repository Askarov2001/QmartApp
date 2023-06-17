package com.example.qmartapp.orderPage

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.MenuActivity
import com.example.qmartapp.R
import com.example.qmartapp.SharedPref
import com.example.qmartapp.addressPage.AddressFragment
import com.example.qmartapp.base.database.BasketEntity
import com.example.qmartapp.basketPage.BasketViewModel
import com.example.qmartapp.data.ClientAddress
import com.example.qmartapp.data.Order
import com.example.qmartapp.data.OrderProduct
import com.example.qmartapp.databinding.FragmentOrderBinding
import com.example.qmartapp.databinding.OrderBottomSheetBinding
import com.example.qmartapp.productsPage.products.Product
import com.google.android.material.chip.Chip
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class OrderFragment : Fragment(R.layout.fragment_order) {
    private val viewModel: OrderViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()
    private val binding: FragmentOrderBinding by viewBinding()
    private lateinit var bottomSheetBinding: OrderBottomSheetBinding

    private var productsSum = 0
    private val products: ArrayList<OrderProduct> = ArrayList()
    private var deliveryCost = 700

    private lateinit var clientAddress: ClientAddress

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setDeliveryCost(700)
        viewModel.setProductsCost(productsSum)
        setToolbar()
        setBottomSheet()
        basketViewModel.getAll()
        observeViewModel()
        binding.nameEditText.setText((requireActivity() as MenuActivity).getValue(SharedPref.NAME))
        binding.commentEditText.setText((requireActivity() as MenuActivity).getValue(SharedPref.PHONE))


        val address: ClientAddress? = arguments?.getParcelable("address")
        if (address != null) {
            clientAddress = address
            updateClientAddressView()
        }

        binding.chooseAddressTextView.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_addressFragment)
            //fragment.show(parentFragmentManager, "Dialog")
        }
    }


    private fun updateClientAddressView() = with(binding) {
        chooseAddressTextView.isGone = true
        clientAddressTextView.text = clientAddress.streetHouse
        addressLinearLayout.isVisible = true
    }


    private fun setBottomSheet() = with(binding) {
        bottomSheetBinding = OrderBottomSheetBinding.bind(binding.root)

        viewModel.orderLiveData.observe(viewLifecycleOwner) {
            with(bottomSheetBinding) {
                it?.productsCost?.let {
                    productsCostTextView.text = "$it т"
                }
                it?.deliveryCost?.let {
                    deliveryCostTextView.text = "$it т"
                }
                viewModel.total.let {
                    totalCostTextView.text = "$it т"
                }
            }
        }

        bottomSheetBinding.toPayButton.setOnClickListener {
            var sellers: String = ""
            products.forEach {
                sellers += it.sellerId
            }
            val order = Order(
                productsCost = productsSum,
                deliveryCost = deliveryCost,
                products = products,
                address = clientAddress.streetHouse,
                totalCost = productsSum + deliveryCost,
                clientName = nameEditText.text.toString(),
                phone = commentEditText.text.toString(),
                sellers = sellers
            )
            viewModel.setOrder(order)
            writeNewOrderToDb(order)
            findNavController().navigate(R.id.action_orderFragment_to_orderResultFragment)
        }


    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                basketViewModel.items.collect {
                    var sum = 0
                    it.forEach {
                        productsSum += (it.price * it.count)
                        viewModel.setProductsCost(productsSum)
                        products.add(
                            OrderProduct(
                                it.name,
                                it.image,
                                it.count,
                                it.price,
                                it.sellerId
                            )
                        )
                        bottomSheetBinding.totalCostTextView.text =
                            "${productsSum + deliveryCost} т"
                    }
                }
            }
        }
    }

    private fun setToolbar() = with(binding) {
        requireActivity().setActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun writeNewOrderToDb(order: Order) {
        database.child("ORDERS").child(order.id).setValue(order)
        basketViewModel.deleteAll()
    }
}