package com.example.qmartapp.favouritesPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qmartapp.base.database.FavouritesEntity
import com.example.qmartapp.productsPage.adapter.ProductsDisplayItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class FavoutitesViewModel(private val repository: FavouritesRepository) : ViewModel(),
    KoinComponent {
    private val _items = MutableStateFlow(ArrayList<FavouritesEntity>())
    val items: StateFlow<List<FavouritesEntity>> = _items
    private val _isFav = MutableStateFlow(false)
    val isFav: StateFlow<Boolean> = _isFav

    fun getAll() {
        viewModelScope.launch {
            var result = ArrayList<FavouritesEntity>()
            withContext(Dispatchers.IO) {
                result = repository.getAll() as ArrayList<FavouritesEntity>
            }
            withContext(Dispatchers.Main) {
                _items.value = result
            }
        }
    }

    fun addToFav(item: ProductsDisplayItem) {
        viewModelScope.launch {
            var result = false

            withContext(Dispatchers.IO) {
                result = repository.add(
                    FavouritesEntity(
                        item.id,
                        item.image,
                        item.title,
                        item.price,
                        1,
                        item.sellerId
                    )
                )
            }
            withContext(Dispatchers.Main) {

            }
        }
    }

    fun deleteItem(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteItem(id)
                getAll()
            }
        }
    }

    fun isFav(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _isFav.value = repository.isFav(id)
            }
        }
    }

    fun addOrDelete(isFav: Boolean, item: ProductsDisplayItem) {
        if (isFav) {
            addToFav(item)
        } else {
            deleteItem(item.id)
        }
    }
}