package com.example.qmartapp.productsPage.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.qmartapp.ProductsActivity
import com.example.qmartapp.R

class RecyclerViewProductAdapter constructor(private val getActivity: ProductsActivity, private val productList: List<Product>):
    RecyclerView.Adapter<RecyclerViewProductAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.Title.text = productList[position].title
        holder.Title2.text = productList[position].title2
        holder.Title3.text = productList[position].title3
        holder.cardImg.setImageResource(productList[position].image)

    }

    class MyViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView) {
        val Title : TextView = itemView.findViewById(R.id.txt_title)
        val Title2 : TextView = itemView.findViewById(R.id.txt_title2)
        val Title3 : TextView = itemView.findViewById(R.id.txt_title3)
        val cardImg : ImageView = itemView.findViewById(R.id.cardImg)
        val cardView : CardView = itemView.findViewById(R.id.cardView)
    }

}