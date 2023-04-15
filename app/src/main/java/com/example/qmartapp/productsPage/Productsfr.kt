package com.example.qmartapp.productsPage

import android.os.Bundle
import android.view.View
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

    private val basketViewModel: BasketViewModel by viewModel()

    private val productsList: ArrayList<ProductsDisplayItem> by lazy {
        arrayListOf(
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            )
        )
    }
    private val electronicList: ArrayList<ProductsDisplayItem> by lazy {
        arrayListOf(
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.electronic),
                getString(R.string.donates_desc),
                8889,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ),
            ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            ), ProductsDisplayItem(
                4.8,
                null,
                R.drawable.donates,
                getString(R.string.donates),
                getString(R.string.donates_desc),
                776,
                getString(R.string.alma_kz),
                onClick = {
                    navigateToInfo(it)
                },
                addAction = {
                    addToBasket(it)
                }
            )
        )
    }

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
            adapter.list = getList(args.menuItem?.id)
            toolbar.title = args.menuItem?.title
        }
    }

    private fun getList(id: String?): ArrayList<ProductsDisplayItem> {
        return when (id) {
            "PRODUCTS" -> {
                productsList
            }

            "ELECTRONIC" -> {
                electronicList
            }

            else -> productsList
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