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
import com.example.qmartapp.R
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
    private var tipCost = 0
    private var selectedDay: String = ""
    private var selectedTime: String = ""

    private lateinit var clientAddress: ClientAddress

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setChipGroups()
        setToolbar()
        setBottomSheet()
        basketViewModel.getAll()
        observeViewModel()
        recalculateAmount()

        val address: ClientAddress? = arguments?.getParcelable("address")
        if (address != null) {
            clientAddress = address
            updateClientAddressView()
        }

        binding.chooseAddressTextView.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_addressFragment)
            //fragment.show(parentFragmentManager, "Dialog")
        }

        binding.chooseCardTextView.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_cardDetailFragment)
        }
    }


    private fun updateClientAddressView() = with(binding) {
        chooseAddressTextView.isGone = true
        clientAddressTextView.text = clientAddress.streetHouse
        addressLinearLayout.isVisible = true
    }


    private fun setBottomSheet() = with(binding) {
        //viewModel.setProductsCost(productsSum)
        //viewModel.setDeliveryCost(700)
        recalculateAmount()

        bottomSheetBinding = OrderBottomSheetBinding.bind(binding.root)

        //println(tipsChipGroup.checkedChipId)

        viewModel.orderLiveData.observe(viewLifecycleOwner) {
            with(bottomSheetBinding) {
                it?.productsCost?.let {
                    productsCostTextView.text = "$it т"
                }
                it?.deliveryCost?.let {
                    deliveryCostTextView.text = "$it т"
                }
                tipsCostTextView.text = "${it?.tipCost} т"
                viewModel.total.let {
                    totalCostTextView.text = "$it т"
                }
            }
        }

        bottomSheetBinding.toPayButton.setOnClickListener {
            val order = Order(
                productsCost = productsSum,
                deliveryCost = deliveryCost,
                tipCost = tipCost,
                products = products,
                address = clientAddress.streetHouse,
                totalCost = viewModel.total,
                clientName = nameEditText.text.toString(),
                comment = commentEditText.text.toString(),
                date = selectedDay,
                time = selectedTime
            )
            viewModel.setOrder(order)
            writeNewOrderToDb(order)
            findNavController().navigate(R.id.action_orderFragment_to_orderResultFragment)
        }


    }

    private fun recalculateAmount() {
        viewModel.recalculateAmount()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                basketViewModel.items.collect {
                    var sum = 0
                    it.forEach {
                        productsSum += (it.price * it.count)
                        products.add(OrderProduct(it.name, it.image, it.count, it.price))
                    }
                }
            }
        }
        recalculateAmount()
    }

    private fun setToolbar() = with(binding) {
        requireActivity().setActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setChipGroups() = with(binding) {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("d MMMM", Locale("ru"))
        val current = formatter.format(calendar.time)
        var nextDay: String

        nextDay = current
        repeat(7) {
            val dateChip = layoutInflater.inflate(
                R.layout.item_chip,
                binding.dateChipGroup,
                false
            ) as Chip
            dateChipGroup.addView(dateChip.apply {
                text = nextDay
                setOnClickListener {
                    selectedDay = nextDay
                }
            })
            if (it == 0) {
                dateChipGroup.check(dateChip.id)
                selectedDay = nextDay
            }
            calendar.add(Calendar.DATE, 1)
            nextDay = formatter.format(calendar.time)
        }

        val timePeriods = mutableListOf<String>()
        var nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        while (nowHour in 8..20) {
            if (nowHour % 2 == 0) {
                ++nowHour
            }
            timePeriods.add("${++nowHour}:00 - ${nowHour + 2}:00")
            nowHour++
        }

        timePeriods.forEachIndexed { index, element ->
            val timeChip = layoutInflater.inflate(
                R.layout.item_chip,
                binding.timeChipGroup,
                false
            ) as Chip
            timeChipGroup.addView(timeChip.apply {
                text = element
                setOnClickListener {
                    selectedTime = element
                }
            })
            if (index == 0) {
                timeChipGroup.check(timeChip.id)
                selectedTime = element
            }
        }

        val tipList = listOf(0, 200, 400, 500, 600, 700)
        tipList.forEachIndexed { index, element ->
            val tipChip = layoutInflater.inflate(
                R.layout.item_chip,
                binding.tipsChipGroup,
                false
            ) as Chip
            tipsChipGroup.addView(tipChip.apply {
                text = "$element т"
                setOnClickListener {
                    tipCost = element
                    recalculateAmount()
                }
            })
            if (index == 0) {
                tipsChipGroup.check(tipChip.id)
            }
        }
    }

    private fun writeNewOrderToDb(order: Order) {
        database.child("ORDERS").child(order.id).setValue(order)
        basketViewModel.deleteAll()
    }
}