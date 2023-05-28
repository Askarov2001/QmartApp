package com.example.qmartapp.basketPage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qmartapp.R
import com.example.qmartapp.base.database.BasketEntity
import com.example.qmartapp.base.database.FavouritesEntity
import com.example.qmartapp.databinding.ItemBasketBinding
import com.example.qmartapp.databinding.ItemFavBinding
import kotlinx.android.extensions.LayoutContainer

class FavAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: List<FavouritesEntity> = ArrayList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    var listener: FavAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is FavItemViewHolder) {
            holder.bind(item, listener)
        }
    }

    class FavItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding: ItemFavBinding = ItemFavBinding.bind(containerView)
        fun bind(item: FavouritesEntity, listener: FavAdapterListener?) {
            binding.apply {
                name.text = item.name
                price.text = binding.root.context.getString(R.string.price_kzt, item.price)
                Glide.with(root.context)
                    .load(item.image)
                    .into(image)
                count.text = "${item.count} шт"
                deleteBtn.setOnClickListener {
                    listener?.onDelete(item.id)
                }
                addBtn.setOnClickListener {
                    listener?.onAdd(item)
                }

            }

        }
    }
}