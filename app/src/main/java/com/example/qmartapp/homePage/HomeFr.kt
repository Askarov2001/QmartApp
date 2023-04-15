package com.example.qmartapp.homePage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.databinding.FragmentHomeBinding
import com.example.qmartapp.homePage.adapter.HomeMenuAdapter
import com.example.qmartapp.homePage.adapter.MenuDisplayItem
import com.example.qmartapp.homePage.adapter.MenuItemDecorator


class HomeFr : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val adapter: HomeMenuAdapter by lazy {
        HomeMenuAdapter()
    }
    override val isRootFragment: Boolean
        get() = true
    private val menuList: ArrayList<MenuDisplayItem> by lazy {
        arrayListOf(
            MenuDisplayItem(
                getString(R.string.products), R.drawable.products, "PRODUCTS"
            ) {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(getString(R.string.appliances), R.drawable.appliances, "APPLIANCES") {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(
                getString(R.string.all_for_home),
                R.drawable.all_for_home,
                "ALL_FOR_HOME"
            ) {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(getString(R.string.furniture), R.drawable.furniture, "FURNITURE") {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(getString(R.string.electronic), R.drawable.electronic, "ELECTRONIC") {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(
                getString(R.string.accessories),
                R.drawable.accessories,
                "ACCESSORIES"
            ) {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(getString(R.string.sport), R.drawable.sport, "SPORT") {
                navigateToProductScreen(it)
            },
            MenuDisplayItem(getString(R.string.make_up), R.drawable.make_up, "MAKE_UP") {
                navigateToProductScreen(it)
            }, MenuDisplayItem(getString(R.string.zoo_products), R.drawable.zoo, "ZOO") {
                navigateToProductScreen(it)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuList.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.menuList.adapter = adapter
        adapter.list = menuList
    }

    private fun navigateToProductScreen(item: MenuDisplayItem) {
        navController?.navigate(HomeFrDirections.actionHomeToProducts(item))
    }
}

