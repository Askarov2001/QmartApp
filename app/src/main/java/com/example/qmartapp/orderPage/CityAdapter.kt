package com.example.qmartapp.orderPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.qmartapp.R

private var checkedPosition = -1
const val EMPTY = "empty"

class CityAdapter() : ListAdapter<String, CityViewHolder>(StringDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            checkedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }
    }

    fun getSelectedIndex(): Int {
        return checkedPosition
    }

    fun getSelectedName(): String {
        if (checkedPosition == -1) {
            return EMPTY
        }
        return getItem(checkedPosition)
    }

    fun setSelectedIndex(index: Int) {
        checkedPosition = index
        notifyDataSetChanged()
    }

    fun reset() {
        checkedPosition = -1
        notifyDataSetChanged()
    }


}

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val cityTextView = itemView.findViewById<TextView>(R.id.cityTextView)

    fun bind(category: String) {
        cityTextView.text = category
        if (checkedPosition == -1) {
            cityTextView.isSelected = false
        } else {
            cityTextView.isSelected = checkedPosition == adapterPosition
        }
    }
}

class StringDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

}


