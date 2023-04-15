package com.example.qmartapp.orderPage

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.addressPage.AddressFragment
import com.example.qmartapp.base.database.BasketEntity
import com.example.qmartapp.basketPage.BasketViewModel
import com.example.qmartapp.data.ClientAddress
import com.example.qmartapp.data.Order
import com.example.qmartapp.databinding.FragmentOrderBinding
import com.example.qmartapp.databinding.OrderBottomSheetBinding
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class OrderFragment : Fragment(R.layout.fragment_order) {
    private val viewModel: OrderViewModel by viewModel()
    private val binding: FragmentOrderBinding by viewBinding()
    private lateinit var bottomSheetBinding: OrderBottomSheetBinding

    private var productsSum = 0
    private var deliveryCost = 700
    private var tipCost = 0

    private lateinit var clientAddress: ClientAddress

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setChipGroups()
        setToolbar()
        setBottomSheet()

        val address: ClientAddress? = arguments?.getParcelable("address")
        if(address != null){
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
        productsSum = arguments?.getInt("sum") ?: 0
        //viewModel.setProductsCost(productsSum)
        //viewModel.setDeliveryCost(700)
        recalculateAmount()

        bottomSheetBinding = OrderBottomSheetBinding.bind(binding.root)

        //println(tipsChipGroup.checkedChipId)

        viewModel.orderLiveData.observe(viewLifecycleOwner) {
            with(bottomSheetBinding){
                it?.productsCost?.let{
                    productsCostTextView.text = "$it т"
                }
                it?.deliveryCost?.let {
                    deliveryCostTextView.text = "$it т"
                }
                tipsCostTextView.text = "${it?.tipCost} т"
                it?.totalCost?.let {
                    totalCostTextView.text = "$it т"
                }
            }
        }

        bottomSheetBinding.toPayButton.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_orderResultFragment)
        }



    }

    private fun recalculateAmount(){
        viewModel.setOrder(
            Order(
                productsCost = productsSum,
                deliveryCost = deliveryCost,
                tipCost = tipCost
            )
        )
        viewModel.recalculateAmount()
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
                false) as Chip
            dateChipGroup.addView(dateChip.apply {
                text = nextDay
            })
            if(it == 0){
                dateChipGroup.check(dateChip.id)
            }
            calendar.add(Calendar.DATE, 1)
            nextDay = formatter.format(calendar.time)
        }

        val timePeriods = mutableListOf<String>()
        var nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        while(nowHour in 8..20){
            if (nowHour % 2 == 0){
                ++nowHour
            }
            timePeriods.add("${++nowHour}:00 - ${nowHour+2}:00")
            nowHour++
        }

        timePeriods.forEachIndexed { index, element ->
            val timeChip = layoutInflater.inflate(
                R.layout.item_chip,
                binding.timeChipGroup,
                false) as Chip
            timeChipGroup.addView(timeChip.apply {
                text = element
            })
            if(index == 0){
                timeChipGroup.check(timeChip.id)
            }
        }

        val tipList = listOf(0,200,400,500,600,700)
        tipList.forEachIndexed { index, element ->
            val tipChip = layoutInflater.inflate(
                R.layout.item_chip,
                binding.tipsChipGroup,
                false) as Chip
            tipsChipGroup.addView(tipChip.apply {
                text = "$element т"
                setOnClickListener {
                    tipCost = element
                    recalculateAmount()
                }
            })
            if(index == 0){
                tipsChipGroup.check(tipChip.id)
            }
        }
    }

}