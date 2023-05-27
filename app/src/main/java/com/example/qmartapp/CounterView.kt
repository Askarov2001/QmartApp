package com.example.qmartapp

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class CounterView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttributeSet: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttributeSet) {

    private val minusView: ImageView
    private val plusView: ImageView
    private val countView: TextView

    init {
        val root = View.inflate(context, R.layout.widget_counter, this)
        countView = root.findViewById(R.id.count)
        plusView = root.findViewById<ImageView>(R.id.plus)
        minusView = root.findViewById<ImageView>(R.id.minus)
        plusView.setOnClickListener {
            count++
            root.callOnClick()
        }
        minusView.setOnClickListener {
            count--
            if (count < 1) {
                count = 1
            }
            root.callOnClick()
        }
    }

    var count: Int = 1
        set(value) {
            countView.text = value.toString()
            field = value
        }
}