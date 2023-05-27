package com.example.qmartapp.basketPage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qmartapp.R
import com.example.qmartapp.base.database.BasketEntity
import com.example.qmartapp.databinding.ItemBasketBinding
import kotlinx.android.extensions.LayoutContainer

class BasketAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<BasketEntity> = ArrayList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    var listener: BasketAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BasketItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is BasketItemViewHolder) {
            holder.bind(item, listener)
        }
    }

    class BasketItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding: ItemBasketBinding = ItemBasketBinding.bind(containerView)
        fun bind(item: BasketEntity, listener: BasketAdapterListener?) {
            binding.apply {
                name.text = item.name
                price.text = binding.root.context.getString(R.string.price_kzt, item.price)
                image.setImageResource(item.image)
                count.text = "1 шт"
                counter.count = item.count
                counter.setOnClickListener {
                    listener?.onCounterClick(item.id,counter.count)
                }
                deleteBtn.setOnClickListener {
                    listener?.onDelete(item.id)
                }
            }

        }
    }
}