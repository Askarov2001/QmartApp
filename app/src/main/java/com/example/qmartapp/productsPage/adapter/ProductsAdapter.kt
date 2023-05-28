package com.example.qmartapp.productsPage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qmartapp.R
import com.example.qmartapp.databinding.ItemProductsBinding
import kotlinx.android.extensions.LayoutContainer

class ProductsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = ArrayList<ProductsDisplayItem>()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is ProductsItemViewHolder) {
            holder.bind(item)
        }
    }

    class ProductsItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        private val binding = ItemProductsBinding.bind(containerView)

        fun bind(item: ProductsDisplayItem) {
            binding.apply {
                title.text = item.title
                description.text = item.subTitle
                Glide.with(root.context)
                    .load(item.image)
                    .into(image)
                rateImage.isVisible = item.rate != null
                rate.isVisible = item.rate != null
                rate.text = item.rate.toString()
                price.text = binding.root.context.getString(R.string.price_kzt, item.price)
                root.setOnClickListener {
                    item.onClick.invoke(item)
                }
                addBtn.setOnClickListener {
                    item.addAction.invoke(item)
                }
            }
        }
    }
}