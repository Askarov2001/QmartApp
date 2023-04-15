package com.example.qmartapp.favouritesPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.base.database.FavouritesEntity
import com.example.qmartapp.basketPage.BasketViewModel
import com.example.qmartapp.basketPage.adapter.FavAdapter
import com.example.qmartapp.basketPage.adapter.FavAdapterListener
import com.example.qmartapp.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : BaseFragment(R.layout.fragment_favourites) {
    private val basketViewModel: BasketViewModel by viewModel()
    private val favoutitesViewModel: FavoutitesViewModel by viewModel()

    private val adapter: FavAdapter by lazy {
        FavAdapter().apply {
            listener = adapterListener
        }
    }
    private val binding: FragmentFavouritesBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoutitesViewModel.getAll()
        binding.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    navController?.popBackStack() ?: parentFragmentManager.popBackStack()
                }
                title = "Избранное"
                favList.layoutManager = GridLayoutManager(requireContext(), 2)
                favList.adapter = adapter
            }
        }
        observeVM()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoutitesViewModel.items.collect {
                    adapter.list = it
                }
            }
        }
    }

    private val adapterListener = object : FavAdapterListener {
        override fun onDelete(id: String) {
            favoutitesViewModel.deleteItem(id)
        }

        override fun onAdd(item: FavouritesEntity) {
            basketViewModel.addToBasket(item)
        }
    }
}