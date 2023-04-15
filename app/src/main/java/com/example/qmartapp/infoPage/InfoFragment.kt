package com.example.qmartapp.infoPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.basketPage.BasketViewModel
import com.example.qmartapp.databinding.FragmentInfoBinding
import com.example.qmartapp.favouritesPage.FavoutitesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : BaseFragment(R.layout.fragment_info) {

    private val args: InfoFragmentArgs by navArgs()
    private val binding: FragmentInfoBinding by viewBinding()
    override val onBackPressedOverrideCallback: () -> Unit = {
        navController?.popBackStack() ?: parentFragmentManager.popBackStack()
    }
    private var isFav: Boolean = false
    private val basketViewModel: BasketViewModel by viewModel()
    private val favoutitesViewModel: FavoutitesViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    navController?.popBackStack() ?: parentFragmentManager.popBackStack()
                }
            }
            if (args.productItem != null) {
                val product = args.productItem!!
                favoutitesViewModel.isFav(product.id)
                image.setImageResource(product.image)
                name.text = product.title
                description.text = product.subTitle
                rate.text = product.rate.toString()
                price.text = getString(R.string.price_kzt, product.price)
                addBtn.setOnClickListener {
                    basketViewModel.addToBasket(product, counter.count)
                    showMessage("added!!")
                    addBudge(counter.count)
                }
                favBtn.setOnClickListener {
                    isFav = !isFav
                    favoutitesViewModel.addOrDelete(isFav, product)
                    binding.apply {
                        if (isFav) {
                            favBtn.setImageResource(R.drawable.ic_fav_colored)
                        } else {
                            favBtn.setImageResource(R.drawable.ic_fav)
                        }
                    }
                }
            }
        }
        observeVM()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoutitesViewModel.isFav.collect {
                    isFav = it
                    binding.apply {
                        if (isFav) {
                            favBtn.setImageResource(R.drawable.ic_fav_colored)
                        } else {
                            favBtn.setImageResource(R.drawable.ic_fav)
                        }
                    }
                }
            }
        }
    }
}