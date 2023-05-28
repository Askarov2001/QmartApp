package com.example.qmartapp.orderPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qmartapp.data.Order
import org.koin.core.component.KoinComponent

class OrderViewModel : ViewModel(), KoinComponent {

    private val orderMutableLiveData = MutableLiveData<Order?>()

    val orderLiveData: LiveData<Order?>
        get() = orderMutableLiveData

    var total = 0

    init {
        orderMutableLiveData.value = Order()
    }

    fun setOrder(order: Order?) {
        orderMutableLiveData.value = order
    }

    fun setProductsCost(cost: Int) {
        setOrder(
            orderMutableLiveData.value?.copy(
                productsCost = cost,
                totalCost = cost + (orderMutableLiveData.value?.deliveryCost ?: 0)
            )
        )
    }

    fun setDeliveryCost(cost: Int) {
        setOrder(
            orderMutableLiveData.value?.copy(
                deliveryCost = cost
            )
        )

    }


}