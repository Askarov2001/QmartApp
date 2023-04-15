package com.example.qmartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qmartapp.productsPage.products.Product
import com.example.qmartapp.productsPage.products.RecyclerViewProductAdapter

class ProductsActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerViewProductAdapter: RecyclerViewProductAdapter? = null
    private var productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productList = ArrayList()

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerViewProductAdapter = RecyclerViewProductAdapter(this@ProductsActivity, productList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewProductAdapter

        prepareProductListData()

    }

    private fun prepareProductListData() {
        var product = Product("Avatar","Adlet","Super", R.drawable.products)
        productList.add(product)
        product = Product("Batman","Adlet","Super", R.drawable.phone)
        productList.add(product)

        product = Product("End Game","Adlet","Super", R.drawable.all_for_home)
        productList.add(product)
        product = Product("Hulk","Adlet","Super", R.drawable.appliances)
        productList.add(product)
        product = Product("End Game","Adlet","Super", R.drawable.products)
        productList.add(product)
        product = Product("Hulk","Adlet","Super", R.drawable.make_up)
        productList.add(product)
        product = Product("End Game","Adlet","Super", R.drawable.electronic)
        productList.add(product)
        product = Product("Hulk","Adlet","Super", R.drawable.furniture)
        productList.add(product)
        product = Product("End Game","Adlet","Super", R.drawable.all_for_home)
        productList.add(product)
        product = Product("Hulk","Adlet","Super", R.drawable.zoo)
        productList.add(product)

        recyclerViewProductAdapter!!.notifyDataSetChanged()

    }
}