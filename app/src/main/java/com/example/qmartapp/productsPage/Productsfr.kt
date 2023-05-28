package com.example.qmartapp.productsPage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.basketPage.BasketViewModel
import com.example.qmartapp.databinding.FragmentProductsBinding
import com.example.qmartapp.productsPage.adapter.ProductsAdapter
import com.example.qmartapp.productsPage.adapter.ProductsDisplayItem
import com.example.qmartapp.productsPage.adapter.ProductsItemDecorator
import com.example.qmartapp.productsPage.products.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class Productsfr : BaseFragment(R.layout.fragment_products) {

    private val args: ProductsfrArgs by navArgs()
    private val binding: FragmentProductsBinding by viewBinding()
    private val adapter: ProductsAdapter by lazy {
        ProductsAdapter()
    }
    override val onBackPressedOverrideCallback: () -> Unit = {
        navController?.popBackStack() ?: parentFragmentManager.popBackStack()
    }

    private val database: DatabaseReference by lazy {
        Firebase.database.reference
    }

    private val basketViewModel: BasketViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener {
                navController?.popBackStack() ?: parentFragmentManager.popBackStack()
            }
            productsListView.layoutManager = GridLayoutManager(requireContext(), 2)
            productsListView.adapter = adapter
            productsListView.addItemDecoration(ProductsItemDecorator())
            getList(args.menuItem?.id)
            toolbar.title = args.menuItem?.title
        }
    }

    private fun getList(id: String?) {
        val products = ArrayList<ProductsDisplayItem>()
        database.get().addOnSuccessListener {
            Log.d("SHAPSHO", it.toString())
            it.children.forEach {
                it.key
            }

        }
        database.child(id.toString()).get().addOnSuccessListener {
            it.children.forEach {
                val product = (it.getValue(Product::class.java))
                product?.let { product ->
                    products.add(
                        ProductsDisplayItem(
                            4.8,
                            null,
                            product.images,
                            product.name,
                            product.description,
                            product.cost,
                            null,
                            {
                                navigateToInfo(it)
                            },
                            product.id,
                            {
                                addToBasket(it)
                            }
                        )
                    )
                }
            }
            Log.d("SHAPSHO", products.toString())
            adapter.list = products
        }
    }

    private fun navigateToInfo(item: ProductsDisplayItem) {
        navController?.navigate(ProductsfrDirections.actionProductToInfo(item))
    }

    private fun addToBasket(item: ProductsDisplayItem) {
        basketViewModel.addToBasket(item)
        showMessage("added!")
    }
}