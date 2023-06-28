package com.example.qmartapp.basketPage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.qmartapp.R
import com.example.qmartapp.base.BaseFragment
import com.example.qmartapp.basketPage.adapter.BasketAdapter
import com.example.qmartapp.basketPage.adapter.BasketAdapterListener
import com.example.qmartapp.databinding.FragmentBasketBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketFragment : BaseFragment(R.layout.fragment_basket) {
    private val viewModel: BasketViewModel by viewModel()
    private val adapter: BasketAdapter by lazy {
        BasketAdapter().apply {
            listener = adapterListener
        }
    }
    private var sum = 0

    private val binding: FragmentBasketBinding by viewBinding()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAll()
        binding.apply {
            toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back)
                setNavigationOnClickListener {
                    navController?.popBackStack() ?: parentFragmentManager.popBackStack()
                }
                title = "Корзина"
                basketList.layoutManager = GridLayoutManager(requireContext(), 2)
                basketList.adapter = adapter
            }
            toPayButton.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("sum", sum)
                navController?.navigate(R.id.action_basketScreen_to_orderFragment, bundle)
            }
        }
        observeVM()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect {
                    adapter.list = it
                    it.forEach {
                        sum += (it.price * it.count)
                    }

                }
            }
        }
    }


    private val adapterListener = object : BasketAdapterListener {
        override fun onDelete(id: String) {
            viewModel.deleteItem(id)
        }

        override fun onCounterClick(id: String, count: Int) {
            viewModel.updateItem(id, count)
        }


    }
}