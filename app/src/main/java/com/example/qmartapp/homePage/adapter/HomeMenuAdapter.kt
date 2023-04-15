package com.example.qmartapp.homePage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qmartapp.R
import com.example.qmartapp.databinding.ItemMenuBinding
import kotlinx.android.extensions.LayoutContainer

class HomeMenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list = ArrayList<MenuDisplayItem>()
        set(value) {
            notifyDataSetChanged()
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MenuItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        if (holder is MenuItemViewHolder) {
            holder.bind(item)
        }
    }

    class MenuItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding: ItemMenuBinding = ItemMenuBinding.bind(containerView)

        fun bind(item: MenuDisplayItem) {
            binding.title.text = item.title
            binding.image.setImageResource(item.image)
            binding.root.setOnClickListener {
                item.onClick.invoke(item)
            }
        }
    }
}